package org.example.canon.controller.response.postResponse;

import org.example.canon.dto.PostDTO;

import java.util.List;

public class PostListResponse {

  private List<PostDTO> posts;

  public PostListResponse(List<PostDTO> posts) {
    this.posts = posts;
  }
}
