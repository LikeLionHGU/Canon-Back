package org.example.canon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.entity.PostLike;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDTO {

    private Long postLikeId;
    private Long userId;
    private Long postId;


// controller 에서 받아서 service로 보낼때

    public static PostLikeDTO of(PostLike postLike) {
        return PostLikeDTO.builder()
                .postLikeId(postLike.getPostLikeId())
                .userId(postLike.getUser().getId())
                .postId(postLike.getPost().getId())
                .build();
    }

}
