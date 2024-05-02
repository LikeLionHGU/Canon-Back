package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

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

  private String fileName;


  private LocalDateTime createdDate;

  private List<String> tools;

  private long viewCount;

  public static PostDTO of(PostRequest postrequest, String imageUrl, String fileName) {
    return PostDTO.builder()
        .content(postrequest.getContent())
        .title(postrequest.getTitle())
        .tools(postrequest.getTools())
        .category(postrequest.getCategory())
        .createdDate(LocalDateTime.now())
        .contact(postrequest.getContact())
        .fileName(fileName)
        .viewCount(0)
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
        .viewCount(post.getViewCount())
        .category(post.getCategory())
        .contact(post.getContact())
        .isConfirmed(post.getIsConfirmed())
        .fileName(post.getFileName())
        .imageURL(post.getImageURL())
        .createdDate(post.getCreatedDate())
        .build();
  }

}
