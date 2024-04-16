package org.example.canon.controller.response.CommentResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.CommentDto;
import org.example.canon.dto.PostDTO;
import org.example.canon.entity.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentResponse {

    private Long commentId;
    private String content;
    private LocalDateTime createdDate;
    private Long userId;
    private Long postId;
    private String userName;

    public CommentResponse(String userName,CommentDto commentDto, Long commentId) {
        this.commentId = commentId;
        this.userName = userName;
        this.userId = commentDto.getUserId();
        this.content = commentDto.getContent();
        this.createdDate = commentDto.getCreatedDate();
        this.postId = commentDto.getPostId();
    }

    public CommentResponse(CommentDto commentDto) {
        this.commentId = commentDto.getCommentId();
        this.userId = commentDto.getUserId();
        this.userName = commentDto.getUserName();
        this.content = commentDto.getContent();
        this.createdDate = commentDto.getCreatedDate();
        this.postId = commentDto.getPostId();
    }
}

