package org.example.canon.controller.response.CommentResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.CommentDTO;

import java.util.List;
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CommentListResponse {

    private List<CommentDTO> comments;

    public CommentListResponse(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
