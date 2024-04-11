package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.controller.response.postResponse.PostResponse;
import org.example.canon.dto.PostDTO;
import org.example.canon.service.PostService;
import org.example.canon.service.S3Uploader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

  private final PostService postService;
  private final S3Uploader s3Uploader;

  @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<PostResponse> upload(
      @RequestParam("image") MultipartFile image, PostRequest request) throws IOException {
    String imageURL = s3Uploader.upload(image, "example");
    PostDTO postDto = PostDTO.of(request, imageURL);
    postService.save(postDto);

    PostResponse response = new PostResponse(postDto);
    return ResponseEntity.ok(response);

  }
}
