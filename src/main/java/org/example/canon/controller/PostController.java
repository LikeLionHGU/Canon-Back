package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.controller.response.ApiResponse;
import org.example.canon.controller.response.PostIdResponse;
import org.example.canon.controller.response.PostResponse;
import org.example.canon.dto.PostDto;
import org.example.canon.entity.Post;
import org.example.canon.service.PostService;
import org.example.canon.service.S3Uploader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/posts")
public class PostController {

    private final S3Uploader s3Uploader;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse> addPost(
            @RequestParam("file") MultipartFile file, @RequestBody PostRequest postForm) throws IOException {

        String filePath=s3Uploader.upload(file, postForm.getTitle()); //s3에 올리고 주소값 받아오기

        Long postId = postService.addPost(PostDto.from(filePath, postForm)); //이미지 주소와 다른 값들을 통해 db에 업로드
        ApiResponse response = new PostIdResponse(postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse> getPost(@PathVariable Long postId) {
        PostDto post = postService.getPost(postId);
        ApiResponse response = new PostResponse(post);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        ApiResponse response = new PostIdResponse(postId);
        return ResponseEntity.ok(response);
    }

        /* 어느 기준으로 가져와야 하는지 미정
    @GetMapping
    public ResponseEntity<ApiResponse> getAllPosts(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        long count = postService.countAllPosts();
        List<PostDto> postsDto = postService.getAllPosts(page, size);
        ApiResponse response = new PostListResponse(postsDto, count);
        return ResponseEntity.ok(response);
    }
*/
}
