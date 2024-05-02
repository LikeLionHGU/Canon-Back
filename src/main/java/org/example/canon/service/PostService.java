package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.AdminConfirmRequest;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.PostDTO;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.example.canon.exception.PostDeleteDisableException;
import org.example.canon.exception.PostEditDisableException;
import org.example.canon.exception.PostNotFoundException;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final S3Uploader s3Uploader;
  private final ToolsService toolsService;


  public long addPost(PostDTO postDTO, String email) {
    System.out.println(email);
    User user = userRepository.findByEmail(email);

    Post post = Post.of(postDTO, user);
    Post ret = postRepository.save(post);

    //PostDTO로 넘어온 Request에 있는 Tools 를 tools 테이블에 넣기
    toolsService.saveTools(ret , postDTO.getTools());



    return post.getId();
  }

  public List<PostDTO> getAllForAdmin() {
    List<Post> posts = postRepository.findAllByIsNotChecked();
    return posts.stream().map(PostDTO::of).toList();
  }

  // 컨펌하는 로직
  public void confirmPost(Long postId, AdminConfirmRequest request) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    post.confirmPost(request.getDecision());
    postRepository.save(post);
  }


  // 수정하는 로직
  // 여기 추가해야함

  public List<PostDTO> getAllForUser() {
    List<Post> posts = postRepository.findAllByConfirmed();
    return posts.stream().map(PostDTO::of).toList();
  }



  //        public List<Post> getAllPostByUserId(Long userId) {
  //
  //            return postRepository.findAllByUserId(userId);
  //
  //        }

  public PostDTO getPost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    post.plusViewCount();
    postRepository.save(post);
    return PostDTO.of(post);
  }

  @Transactional
  public void deletePost(Long postId, CustomOAuth2UserDTO userDTO) {
    Optional<Post> post = postRepository.findById(postId);
    String imageName = post.get().getFileName();
    System.out.println("==="+ imageName+"===");
    s3Uploader.deleteFile("example",imageName);

    if (userDTO.getEmail().equals(post.get().getUser().getEmail())) {
      postRepository.deleteById(postId);
    } else {
      throw new PostDeleteDisableException();
    }


  }


  @Transactional
  public void updatePost(Long postId, PostRequest request, CustomOAuth2UserDTO userDTO, MultipartFile image) throws IOException {
    Optional<Post> post = postRepository.findById(postId);
    if ((userDTO.getEmail().equals(post.get().getUser().getEmail())) ) {
        if(image.getName().equals(post.get().getFileName())){

        // 파일 뺴고 나머지 내용을 Request로 업데이트 해준다.
          post.get().updatePostOnly(request);

        }else{

          // 원래 파일 삭제
          // 새로 업로드 후 URL + Request 내용으로 업데이트 해준다.
        s3Uploader.deleteFile("example", post.get().getFileName());
        String imageURL = s3Uploader.upload(image, "example");
        post.get().updatePostAndFile(request,imageURL,image.getName());

        }
      } else {
      throw new PostEditDisableException();
    }


  }

}
