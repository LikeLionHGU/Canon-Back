package org.example.canon.controller.response;


import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.PostDto;
import org.example.canon.entity.User;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PostResponse extends ApiResponse {

  private Long postId;

  private String title;
  private String content;
  private String fileUrl; //파일의 url

  private Long userId;

  public PostResponse(PostDto post) {
    this.postId = post.getPostId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.fileUrl = post.getFileUrl();
    this.userId = post.getUserId();
  }
}
