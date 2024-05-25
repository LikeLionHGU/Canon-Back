package org.example.canon.controller.response.postLikeResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.PostLikeDTO;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PostLikeListResponse {

    private List<PostLikeDTO> postLikes;

    public PostLikeListResponse(List<PostLikeDTO> postLikes) {
        this.postLikes = postLikes;
    }
}
