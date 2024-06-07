package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.entity.Comment;
import org.example.canon.entity.Image;
import org.example.canon.entity.Post;
import org.example.canon.entity.Profile;
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

  private String university;

  private String content;

  private String title;

  private String category;

  private String major;

  private String year;

  private String contact;

  private int likeCount;

  private String videoURL;

  private byte isConfirmed;

  private List<Image> images;

  private List<String> comments;
  private long commentsCounts;




  private LocalDateTime createdDate;

  private List<String> tools;

  private long viewCount;

  private String profileImageURL;
  private String profileContact;
  private String profileInfo;
  private String profileName;
  private String profileContribution;




  public static PostDTO of(PostRequest postrequest, List<Image> images) {
    return PostDTO.builder()
            .content(postrequest.getContent())
            .title(postrequest.getTitle())
            .major(postrequest.getMajor())
            .year(postrequest.getYear())
            .tools(postrequest.getTools())
            .videoURL(postrequest.getVideoURL())
            .category(postrequest.getCategory())
            .createdDate(LocalDateTime.now())
            .university(postrequest.getUniversity())
            .contact(postrequest.getContact())
            .likeCount(0)
            .images(images)
            .viewCount(0L)
            .isConfirmed((byte) 0)
            .build();
  }

  public static PostDTO of(Post post) {
    List<String> toolNames = post.getTools().stream()
            .map(tool -> tool.getTool())
            .collect(Collectors.toList());

    List<String> commentss = post.getComments().stream()
            .map(comment -> comment.getContent())
            .collect(Collectors.toList());

    return PostDTO.builder()
            .id(post.getId())
            .userId(post.getUser().getId())
            .userName(post.getUser().getUsername())
            .content(post.getContent())
            .title(post.getTitle())
            .viewCount(post.getViewCount())
            .university(post.getUniversity())
            .major(post.getMajor())
            .images(post.getImages())
            .year(post.getYear())
            .likeCount(post.getCountLike())
            .commentsCounts(post.getComments().size())
            .videoURL(post.getVideoURL())
            .comments(commentss)
            .tools(toolNames)
            .category(post.getCategory())
            .contact(post.getContact())
            .isConfirmed(post.getIsConfirmed())
            .createdDate(post.getCreatedDate())
            .build();
  }
  public static PostDTO of(Post post, Profile profile) {
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
            .commentsCounts(post.getComments().size())
            .major(post.getMajor())
            .year(post.getYear())
            .university(post.getUniversity())
            .tools(toolNames)
            .category(post.getCategory())
            .contact(post.getContact())
            .isConfirmed(post.getIsConfirmed())
            .createdDate(post.getCreatedDate())
            .profileContact(profile.getContact())
            .profileInfo(profile.getInfo())
            .profileName(profile.getName())
            .profileContribution(profile.getContribution())
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
            .videoURL(post.getVideoURL())
            .university(post.getUniversity())
            .major(post.getMajor())
            .likeCount(post.getLikeCount())
            .comments(post.getComments())
            .commentsCounts(post.getComments().size())
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

    List<String> commentss = post.getComments().stream()
            .map(comment -> comment.getContent())
            .collect(Collectors.toList());

    return PostDTO.builder()
            .id(post.getId())
            .userId(post.getUser().getId())
            .userName(post.getUser().getUsername())
            .content(post.getContent())
            .title(post.getTitle())
            .likeCount(post.getCountLike())
            .viewCount(post.getViewCount())
            .university(post.getUniversity())
            .commentsCounts(post.getComments().size())
            .videoURL(post.getVideoURL())
            .major(post.getMajor())
            .year(post.getYear())
            .images(images)
            .tools(toolNames)
            .comments(commentss)
            .category(post.getCategory())
            .contact(post.getContact())
            .isConfirmed(post.getIsConfirmed())
            .createdDate(post.getCreatedDate())
            .build();
  }

}
