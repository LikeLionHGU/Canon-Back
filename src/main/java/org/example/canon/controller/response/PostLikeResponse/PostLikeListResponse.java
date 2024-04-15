package org.example.canon.controller.response.PostLikeResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.CommentDto;
import org.example.canon.dto.PostLikeDto;
import org.example.canon.entity.PostLike;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PostLikeListResponse {

    private List<PostLikeDto> postLikes;

    public PostLikeListResponse(List<PostLikeDto> postLikes) {
        this.postLikes = postLikes;
    }
}
