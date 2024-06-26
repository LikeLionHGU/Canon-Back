package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.PostController;
import org.example.canon.controller.request.AdminConfirmRequest;
import org.example.canon.controller.request.PostFilterRequest;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.ProfileDTO;
import org.example.canon.entity.*;
import org.example.canon.exception.PostDeleteDisableException;
import org.example.canon.exception.PostEditDisableException;
import org.example.canon.exception.PostNotFoundException;
import org.example.canon.repository.*;
import org.example.canon.specification.PostSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.tools.Tool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final ImagesRepository imagesRepository;
  private final S3Uploader s3Uploader;
  private final ToolsService toolsService;
  private final ImagesService imagesService;
  private final ToolsRepository toolsRepository;
  private final ProfileRepository profileRepository;


  public long addPost(PostDTO postDTO, String email) {
    System.out.println("hello"+ email);
    User user = userRepository.findByEmail(email);
    System.out.println(user.getUsername());

    Post post = Post.of(postDTO, user);
    Post ret = postRepository.save(post);

    //PostDTO로 넘어온 Request에 있는 Tools 를 tools 테이블에 넣기
    toolsService.saveTools(ret, postDTO.getTools() );
    imagesService.saveImages(ret, postDTO.getImages());

    return post.getId();
  }

  public List<PostDTO> getAllForAdmin() {

    List<PostDTO> returnPosts = new ArrayList<>();

    List<PostDTO> posts = postRepository.findAll()
            .stream()
            .map(PostDTO::of)
            .toList();

    for (PostDTO nPost : posts) {
      List<Image> images = imagesService.getAllImagesByPostId(nPost.getId());

      returnPosts.add(PostDTO.of(nPost, images));
    }
    return returnPosts;
  }

  // 컨펌하는 로직
  public void confirmPost(Long postId, AdminConfirmRequest request) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    post.confirmPost(request.getDecision());
    postRepository.save(post);
  }

  public List<PostDTO> getAllFilteredPost(PostFilterRequest postFilterRequest) {

    Specification<Post> spec = Specification.where(PostSpecification.filterByCategory(postFilterRequest.getCategory())
            .and(PostSpecification.filterByMajor(postFilterRequest.getMajor())
                    .and(PostSpecification.filterByYear(postFilterRequest.getYear()))));


    List<PostDTO> returnPosts = new ArrayList<>();
    List<PostDTO> posts = postRepository.findAll(spec)
            .stream()
            .map(PostDTO::of)
            .toList();

    for (PostDTO nPost : posts) {
      List<Image> images = imagesService.getAllImagesByPostId(nPost.getId());

      returnPosts.add(PostDTO.of(nPost, images));
    }

    return returnPosts;

  }

  public List<PostDTO> getAllDenied() {
    List<Post> posts = postRepository.findAllByIsDenied();
    return posts.stream().map(PostDTO::of).toList();
  }


  // 수정하는 로직
  // 여기 추가해야함

  public List<PostDTO> getAllPost() {
    List<Post> posts = postRepository.findAllByConfirmed();
    List<PostDTO> returnPosts = new ArrayList<>();

    for (Post post : posts) {
      List<Image> images = imagesService.getAllImagesByPostId(post.getId());
      returnPosts.add(PostDTO.of(post, images));
    }
    return returnPosts;
  }


  //        public List<Post> getAllPostByUserId(Long userId) {
  //
  //            return postRepository.findAllByUserId(userId);
  //
  //        }

  public PostDTO getPost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    Profile profile = profileRepository.findByUser(post.getUser());
    post.plusViewCount();
    postRepository.save(post);
//    if (profile == null) {
//      User user = post.getUser();
//      profile.setName(user.getName());
//      profile.setContact(user.getEmail());
//    }
      return PostDTO.of(post, profile);

  }

  @Transactional
  public void deletePost(Long postId, CustomOAuth2UserDTO userDTO) {
    Optional<Post> post = postRepository.findById(postId);
    if (post.isPresent()) {
      Post posts = post.get();
      List<Image> images = imagesRepository.findAllByPost(posts);
      for(Image image : images){ //반복문으로 하나하나 지우기
        String imageName = image.getFileName();
        System.out.println("===" + imageName + "===");
        s3Uploader.deleteFile("example", imageName);
      }
      imagesRepository.deleteAllByPostId(postId);
      toolsRepository.deleteAllByPostId(postId);
    }

    if (userDTO.getEmail().equals(post.get().getUser().getEmail())) {
      postRepository.deleteById(postId);
    } else {
      throw new PostDeleteDisableException();
    }
  }



  @Transactional
  public void updatePost(Long postId, CustomOAuth2UserDTO userDTO, PostDTO postDTO, List<String> toolString ) throws IOException {
    Optional<Post> post = postRepository.findById(postId);
    List<Tools> toolsIn = toolsRepository.findAllByPost(post.get());
    Tools tools = Tools.of(toolString.get(0),post.get());
    if ((userDTO.getEmail().equals(post.get().getUser().getEmail())) ) {
      post.get().updatePostWithDto(postDTO);

      toolsIn.set(0,tools);
    } else {
      throw new PostEditDisableException();
    }

  }


}
