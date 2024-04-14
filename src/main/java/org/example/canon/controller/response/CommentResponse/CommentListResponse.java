package org.example.canon.controller.response.CommentResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.CommentDto;
import org.example.canon.dto.PostDTO;

import java.util.List;
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CommentListResponse {

    private List<CommentDto> comments;

    public CommentListResponse(List<CommentDto> comments) {
        this.comments = comments;
    }
}
