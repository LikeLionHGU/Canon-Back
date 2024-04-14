package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.CommentRequest;
import org.example.canon.controller.response.CommentResponse.CommentResponse;
import org.example.canon.controller.response.PostLikeResponse.LikeIdResponse;
import org.example.canon.dto.CommentDto;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.entity.Comment;
import org.example.canon.entity.PostLike;
import org.example.canon.service.CommentService;
import org.example.canon.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@Controller
@RequiredArgsConstructor
public class LikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<LikeIdResponse> addComment(@AuthenticationPrincipal CustomOAuth2UserDto userDto, @PathVariable Long postId) {
        PostLike postLike = postLikeService.addLike(userDto.getEmail(), postId);
        LikeIdResponse response = new LikeIdResponse(postLike.getPostLikeId());
        return ResponseEntity.ok(response);
    }

}
