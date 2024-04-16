package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.CommentRequest;
import org.example.canon.controller.response.ApiResponse;
import org.example.canon.controller.response.CommentResponse.CommentIdResponse;
import org.example.canon.controller.response.CommentResponse.CommentListResponse;
import org.example.canon.controller.response.CommentResponse.CommentResponse;
import org.example.canon.dto.CommentDto;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.entity.Comment;
import org.example.canon.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add/{postId}")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest commentRequest
    , @AuthenticationPrincipal CustomOAuth2UserDto userDto, @PathVariable Long postId) {
        Comment comment = commentService.addComment(userDto.getEmail(),CommentDto.from(commentRequest, postId));
        CommentResponse response = new CommentResponse(CommentDto.of(comment));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getOne/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long commentId) {
        CommentDto commentDto = commentService.getComment(commentId);
        CommentResponse response = new CommentResponse(commentDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byPost/{postId}")
    public ResponseEntity<CommentListResponse> getAllCommentByPost(@PathVariable Long postId) {
        List<CommentDto> comments = commentService.getAllForPost(postId);
        CommentListResponse response = new CommentListResponse(comments);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal CustomOAuth2UserDto userDto, @PathVariable Long commentId) {
        commentService.deleteComment(commentId, userDto);
        return ResponseEntity.ok().build();
    }
}

