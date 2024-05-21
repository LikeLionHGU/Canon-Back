package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.entity.Image;
import org.example.canon.entity.Post;

import java.time.LocalDateTime;
import java.util.Date;
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

  private String major;

  private String year;

  private String contact;

  private byte isConfirmed;

  private List<Image> images;

  private LocalDateTime createdDate;

  private List<String> tools;

  private long viewCount;

  public static PostDTO of(PostRequest postrequest, List<Image> images) {
    return PostDTO.builder()
            .content(postrequest.getContent())
            .title(postrequest.getTitle())
            .major(postrequest.getMajor())
            .year(postrequest.getYear())
            .tools(postrequest.getTools())
            .category(postrequest.getCategory())
            .createdDate(LocalDateTime.now())
            .contact(postrequest.getContact())
            .images(images)
            .viewCount(0L)
            .isConfirmed((byte) 0)
            .build();
  }

  public static PostDTO of(Post post) { //이미지 관련은 없음. 따로 넣어주기 -> 아마 get로직인듯
    return PostDTO.builder()
            .id(post.getId())
            .userId(post.getUser().getId())
            .userName(post.getUser().getUsername())
            .content(post.getContent())
            .title(post.getTitle())
            .viewCount(post.getViewCount())
            .major(post.getMajor())
            .year(post.getYear())
            .category(post.getCategory())
            .contact(post.getContact())
            .isConfirmed(post.getIsConfirmed())
            .createdDate(post.getCreatedDate())
            .build();
  }

  public static PostDTO of(PostDTO post, List<Image> images) {
    return PostDTO.builder()
            .id(post.getId())
            .userId(post.getUserId())
            .userName(post.getUserName())
            .content(post.getContent())
            .title(post.getTitle())
            .viewCount(post.getViewCount())
            .major(post.getMajor())
            .year(post.getYear())
            .images(images)
            .category(post.getCategory())
            .contact(post.getContact())
            .isConfirmed(post.getIsConfirmed())
            .createdDate(post.getCreatedDate())
            .build();
  }

  public static PostDTO of(Post post, List<Image> images) {
    return PostDTO.builder()
            .id(post.getId())
            .userId(post.getUser().getId())
            .userName(post.getUser().getUsername())
            .content(post.getContent())
            .title(post.getTitle())
            .viewCount(post.getViewCount())
            .major(post.getMajor())
            .year(post.getYear())
            .images(images)
            .category(post.getCategory())
            .contact(post.getContact())
            .isConfirmed(post.getIsConfirmed())
            .createdDate(post.getCreatedDate())
            .build();
  }

}
