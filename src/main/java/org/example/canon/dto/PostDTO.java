package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.entity.Post;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

  private Long id;
  private Long userId;
  private String userName;

  private String content;

  private String title;

  private String category;

  private String contact;

  private byte isConfirmed;

  private String imageURL;

  private LocalDateTime createdDate;

  public static PostDTO of(PostRequest postrequest, String imageUrl) {
    return PostDTO.builder()
        .content(postrequest.getContent())
        .title(postrequest.getTitle())
        .category(postrequest.getCategory())
        .createdDate(LocalDateTime.now())
        .contact(postrequest.getContact())
        .isConfirmed((byte) 0)
        .imageURL(imageUrl)
        .build();
  }

  public static PostDTO of(Post post) {
    return PostDTO.builder()
        .id(post.getId())
        .userId(post.getUser().getId())
        .userName(post.getUser().getUsername())
        .content(post.getContent())
        .title(post.getTitle())
        .category(post.getCategory())
        .contact(post.getContact())
        .isConfirmed(post.getIsConfirmed())
        .imageURL(post.getImageURL())
        .createdDate(post.getCreatedDate())
        .build();
  }
}
