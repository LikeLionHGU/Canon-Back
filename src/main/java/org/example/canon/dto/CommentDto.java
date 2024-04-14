package org.example.canon.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.controller.request.CommentRequest;
import org.example.canon.entity.Comment;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long commentId;
    private String content;
    private String createdDate;
    private Long userId;
    private Long postId;
    private String userName;


// controller 에서 받아서 service로 보낼때
    public static CommentDto from(CommentRequest commentRequest) {
        return CommentDto.builder()
                .content(commentRequest.getContent())
                .postId(commentRequest.getPostId())
                .build();
    }

    public static CommentDto of(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .userName(comment.getUser().getUsername())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .build();
    }

}
