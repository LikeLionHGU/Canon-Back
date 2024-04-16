package org.example.canon.controller.response.PostLikeResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.controller.response.ApiResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LikeIdResponse extends ApiResponse {

    private Long postLikeId;
}
