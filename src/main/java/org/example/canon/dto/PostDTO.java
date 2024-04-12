package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.PostRequest;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {


  private Long id;
  private Long userId;

  private String content;

  private String title;

  private String category;

  private String contact;

  private byte isComfirmed;

  private String imageURL;

  private LocalDateTime createdDate;

    public static PostDTO of(PostRequest postrequest, String imageUrl) {
    return PostDTO.builder()
        .content(postrequest.getContent())
        .title(postrequest.getTitle())
        .category(postrequest.getCategory())
        .createdDate(LocalDateTime.now())
        .contact(postrequest.getContact())
        .isComfirmed((byte) 0)
        .imageURL(imageUrl)
        .build();
  }
}
