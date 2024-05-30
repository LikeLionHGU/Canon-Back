package org.example.canon.controller.response.postResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.PostDTO;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PostListResponse {

  private List<PostDTO> posts;

  public PostListResponse(List<PostDTO> posts) {
    this.posts = posts;
  }
}
