package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.entity.Image;
import org.example.canon.entity.Post;
import org.example.canon.entity.Tools;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

  public static PostDTO of(Post post) {
    List<String> toolNames = post.getTools().stream()
            .map(tool -> tool.getTool())
            .collect(Collectors.toList());
    return PostDTO.builder()
            .id(post.getId())
            .userId(post.getUser().getId())
            .userName(post.getUser().getUsername())
            .content(post.getContent())
            .title(post.getTitle())
            .viewCount(post.getViewCount())
            .major(post.getMajor())
            .year(post.getYear())
            .tools(toolNames)
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
            .tools(post.getTools())
            .category(post.getCategory())
            .contact(post.getContact())
            .isConfirmed(post.getIsConfirmed())
            .createdDate(post.getCreatedDate())
            .build();
  }

  public static PostDTO of(Post post, List<Image> images) {
    List<String> toolNames = post.getTools().stream()
            .map(tool -> tool.getTool())
            .collect(Collectors.toList());
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
            .tools(toolNames)
            .category(post.getCategory())
            .contact(post.getContact())
            .isConfirmed(post.getIsConfirmed())
            .createdDate(post.getCreatedDate())
            .build();
  }

}
