package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.CommentRequest;
import org.example.canon.controller.response.CommentResponse.CommentListResponse;
import org.example.canon.controller.response.CommentResponse.CommentResponse;
import org.example.canon.controller.response.PostLikeResponse.LikeIdResponse;
import org.example.canon.controller.response.PostLikeResponse.PostLikeListResponse;
import org.example.canon.dto.CommentDto;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.dto.PostLikeDto;
import org.example.canon.entity.Comment;
import org.example.canon.entity.PostLike;
import org.example.canon.service.CommentService;
import org.example.canon.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@Controller
@RequiredArgsConstructor
public class LikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/add/{postId}")
    public ResponseEntity<LikeIdResponse> addLike(@AuthenticationPrincipal CustomOAuth2UserDto userDto, @PathVariable Long postId) {
        PostLike postLike = postLikeService.addLike(userDto.getEmail(), postId);
        LikeIdResponse response = new LikeIdResponse(postLike.getPostLikeId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byPost/{postId}")
    public ResponseEntity<PostLikeListResponse> getAllLikeByPost(@PathVariable Long postId) {
        List<PostLikeDto> postLikes = postLikeService.getAllForLikesByPost(postId);
        PostLikeListResponse response = new PostLikeListResponse(postLikes);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<Void> deleteLike(
            @AuthenticationPrincipal CustomOAuth2UserDto userDto, @PathVariable Long likeId) {
        postLikeService.deleteLike(likeId, userDto);
        return ResponseEntity.ok().build();
    }

}
