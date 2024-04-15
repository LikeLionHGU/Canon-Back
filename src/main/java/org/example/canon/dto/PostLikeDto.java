package org.example.canon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.controller.request.CommentRequest;
import org.example.canon.entity.Comment;
import org.example.canon.entity.PostLike;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDto {

    private Long postLikeId;
    private Long userId;
    private Long postId;


// controller 에서 받아서 service로 보낼때

    public static PostLikeDto of(PostLike postLike) {
        return PostLikeDto.builder()
                .postLikeId(postLike.getPostLikeId())
                .userId(postLike.getUser().getId())
                .postId(postLike.getPost().getId())
                .build();
    }

}
