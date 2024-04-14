package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.CommentRequest;
import org.example.canon.controller.response.CommentResponse.CommentListResponse;
import org.example.canon.controller.response.CommentResponse.CommentResponse;
import org.example.canon.dto.CommentDto;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest commentRequest
    , @AuthenticationPrincipal CustomOAuth2UserDto userDto) {
        Long commentId = commentService.addComment(userDto.getEmail(),CommentDto.from(commentRequest));
        CommentResponse response = new CommentResponse(userDto.getUsername(), CommentDto.from(commentRequest),commentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long commentId) {
        CommentDto commentDto = commentService.getComment(commentId);
        CommentResponse response = new CommentResponse(commentDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CommentListResponse> getAllCommentById(@PathVariable Long postId) {
        List<CommentDto> comments = commentService.getAllForPost(postId);
        CommentListResponse response = new CommentListResponse(comments);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}

