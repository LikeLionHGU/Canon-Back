package org.example.canon.controller.response.postLikeResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.controller.response.ApiResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeTrueOrFalse extends ApiResponse {

    private String postLikeTF;

    public LikeTrueOrFalse (String in){
        this.postLikeTF= in;
    }
}
