package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.controller.response.postResponse.PostResponse;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.dto.PostDTO;
import org.example.canon.entity.Post;
import org.example.canon.service.PostService;
import org.example.canon.service.S3Uploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

  @Value("$${spring.jwt.secret}")
  private String SECRET_KEY;

  private final PostService postService;
  private final S3Uploader s3Uploader;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<PostResponse> uploadPost(
          @RequestParam("image") MultipartFile image, PostRequest request, @AuthenticationPrincipal CustomOAuth2UserDto userDto) throws IOException {

    String imageURL = s3Uploader.upload(image, "example");
    PostDTO postDto = PostDTO.of(request, imageURL);
    System.out.println("===controller "+userDto.getEmail()+"===");
    System.out.println("===controller "+userDto.getName()+"===");
    System.out.println("===controller "+userDto.getUsername()+"===");

    Long postId = postService.save(postDto,userDto.getEmail());

    PostResponse response = new PostResponse(postDto,postId);
    return ResponseEntity.ok(response);

  }


}
