package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.PostFilterRequest;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.controller.response.postResponse.PostListResponse;
import org.example.canon.controller.response.postResponse.PostResponse;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.ToolDTO;
import org.example.canon.entity.Image;
import org.example.canon.entity.Post;
import org.example.canon.service.ImagesService;
import org.example.canon.service.PostService;
import org.example.canon.service.S3Uploader;
import org.example.canon.service.ToolsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

  @Value("$${spring.jwt.secret}")
  private String SECRET_KEY;

  private final PostService postService;
  private final S3Uploader s3Uploader;
  private final ToolsService toolsService;
  private final ImagesService imagesService;

  //done
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<PostResponse> uploadPost(
          @RequestParam(name = "image",required = false) MultipartFile[] image,
          PostRequest request,
          @AuthenticationPrincipal CustomOAuth2UserDTO userDto)
          throws IOException {
    List<Image> images = new ArrayList<>();
    for(int i=0; i< image.length; i++) {
      String filename = image[i].getOriginalFilename();
      String imageUrl = s3Uploader.upload(image[i], "example");

      Image uploadImage = new Image(filename, imageUrl);
      images.add(uploadImage);

    }

    PostDTO postDto = PostDTO.of(request, images);

    Long postId = postService.addPost(postDto, userDto.getEmail());

    PostResponse response = new PostResponse(postDto, postId, userDto.getUsername());
    return ResponseEntity.ok(response);
  }


  @GetMapping("/get/{postId}")
  public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
    PostDTO postDto = postService.getPost(postId);
    List<ToolDTO> toolDto = toolsService.getAllByPostId(postId);
    List<Image> images = imagesService.getAllImagesByPostId(postId);
    PostResponse response = new PostResponse(postDto,toolDto, images);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<Void> deletePost(
          @AuthenticationPrincipal CustomOAuth2UserDTO userDto, @PathVariable Long postId) {
    postService.deletePost(postId, userDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/filtered")
  public ResponseEntity<PostListResponse> getFilteredPosts( PostFilterRequest postFilterRequest){

    List<PostDTO> posts = postService.getAllFilteredPost(postFilterRequest);
    PostListResponse response = new PostListResponse(posts);
    return ResponseEntity.ok(response);
  }

  //보류. 아직 안함
  @PatchMapping("/update/{postId}")
  public ResponseEntity<Void> updatePost(
          @AuthenticationPrincipal CustomOAuth2UserDTO userDto,
          @RequestParam(name = "image",required = false) MultipartFile[] image,
          PostRequest request,
          @PathVariable Long postId
  ) throws IOException {

    List<Image> images = new ArrayList<>();
    for(int i=0; i< image.length; i++) {
      String filename = image[i].getOriginalFilename();
      String imageUrl = s3Uploader.upload(image[i], "example");

      Image uploadImage = new Image(filename, imageUrl);
      images.add(uploadImage);

    }

    PostDTO postDto = PostDTO.of(request, images);

    postService.updatePost(postId, userDto, postDto);
    return ResponseEntity.ok().build();
  }


  @GetMapping("/main")
  public ResponseEntity<PostListResponse> getAllPosts() {
    List<PostDTO> posts = postService.getAllForUser();
    PostListResponse response = new PostListResponse(posts);
    return ResponseEntity.ok(response);
  }


}
