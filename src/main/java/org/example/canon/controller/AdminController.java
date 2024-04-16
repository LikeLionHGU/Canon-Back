package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.AdminConfirmRequest;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.controller.response.postResponse.PostListResponse;
import org.example.canon.dto.PostDTO;
import org.example.canon.service.PostService;
import org.example.canon.service.S3Uploader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

  private final PostService postService;

  @GetMapping("/all")
  public ResponseEntity<PostListResponse> getAllConfirmedPost() {
    List<PostDTO> posts = postService.getAllForAdmin();
    PostListResponse response = new PostListResponse(posts);
    return ResponseEntity.ok(response);
  }

  // 컨펌하는 로직 작성하기
  @PatchMapping("/{postId}")
  public ResponseEntity<Void> confirmPost(@PathVariable Long postId , @RequestBody AdminConfirmRequest  request) {
    postService.confirmPost(postId,request);
    return ResponseEntity.ok().build();
  }

}
