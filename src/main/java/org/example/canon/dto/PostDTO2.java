package org.example.canon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.entity.Image;
import org.example.canon.entity.Post;
import org.example.canon.entity.Profile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO2 {

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

  private List<String> imagesURL;

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






  public static PostDTO2 of(Post post) {
    List<String> toolNames = post.getTools().stream()
            .map(tool -> tool.getTool())
            .collect(Collectors.toList());

    List<String> commentss = post.getComments().stream()
            .map(comment -> comment.getContent())
            .collect(Collectors.toList());

    return PostDTO2.builder()
            .id(post.getId())
            .userId(post.getUser().getId())
            .userName(post.getUser().getUsername())
            .content(post.getContent())
            .title(post.getTitle())
            .viewCount(post.getViewCount())
            .university(post.getUniversity())
            .major(post.getMajor())
            .imagesURL(post.getImages().stream()
                    .map(image -> image.getImageURL())
                    .collect(Collectors.toList()))
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
  public static PostDTO2 of(PostDTO postDTO, List<Image> images) {
    return PostDTO2.builder()
            .id(postDTO.getId())
            .userId(postDTO.getUserId())
            .userName(postDTO.getUserName())
            .content(postDTO.getContent())
            .title(postDTO.getTitle())
            .viewCount(postDTO.getViewCount())
            .videoURL(postDTO.getVideoURL())
            .university(postDTO.getUniversity())
            .major(postDTO.getMajor())
            .likeCount(postDTO.getLikeCount())
            .comments(postDTO.getComments())
            .commentsCounts(postDTO.getComments().size())
            .year(postDTO.getYear())
            .imagesURL(images.stream()
                    .map(Image::getImageURL) // Using images parameter to map URLs
                    .collect(Collectors.toList()))
            .tools(postDTO.getTools())
            .category(postDTO.getCategory())
            .contact(postDTO.getContact())
            .isConfirmed(postDTO.getIsConfirmed())
            .createdDate(postDTO.getCreatedDate())
            .build();
  }


}
