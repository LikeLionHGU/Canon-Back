package org.example.canon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.controller.request.CommentRequest;
import org.example.canon.entity.Comment;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long commentId;
    private String content;
    private LocalDateTime createdDate;
    private Long userId;
    private Long postId;
    private String userName;


// controller 에서 받아서 service로 보낼때
    public static CommentDTO from(CommentRequest commentRequest, Long postId) {
        return CommentDTO.builder()
                .content(commentRequest.getContent())
                .createdDate(LocalDateTime.now())
                .postId(postId)
                .build();
    }

    public static CommentDTO of(Comment comment) {
        return CommentDTO.builder()
                .commentId(comment.getCommentId())
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .createdDate(LocalDateTime.now())
                .userName(comment.getUser().getUsername())
                .content(comment.getContent())
                .build();
    }

}
