package org.example.canon.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.UserDTO;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.example.canon.exception.PostDeleteDisableException;
import org.example.canon.exception.PostNotFoundException;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  public long addPost(PostDTO postDTO, String email) {
    System.out.println(email);
    User user = userRepository.findByEmail(email);

    Post post = Post.of(postDTO, user);
    postRepository.save(post);

    return post.getId();
  }

  public List<PostDTO> getAllForAdmin() {
    List<Post> posts = postRepository.findAllByIsNotChecked();
    return posts.stream().map(PostDTO::of).toList();
  }

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
    return PostDTO.of(post);
  }

  public void deletePost(Long postId, CustomOAuth2UserDto userDTO) {
    Optional<Post> post = postRepository.findById(postId);
    if (userDTO.getEmail().equals(post.get().getUser().getEmail())) {
      postRepository.deleteById(postId);
    } else {
      throw new PostDeleteDisableException();
    }
  }
}
