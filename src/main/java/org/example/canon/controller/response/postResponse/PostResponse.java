package org.example.canon.controller.response.postResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.CommentDTO;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.ProfileDTO;
import org.example.canon.dto.ToolDTO;
import org.example.canon.entity.Comment;
import org.example.canon.entity.Image;
import org.example.canon.entity.ImageOnlyURL;
import org.example.canon.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponse {

  private Long id;
  private Long userId;

  private String userName;
  private String university;
  private String title;
  private String content;
  private String category;

  private LocalDateTime createdDate;

  private List<ImageOnlyURL> imageURLs;

  private String contact;
  private int likeCount;

  private byte isConfirmed;
  private String videoURL;
  private List<String> comments;

  private List<String> tools;

  private long commentsCounts;
  private long viewCount;
  private String profileImageURL;
  private String profileContact;
  private String profileInfo;
  private String profileName;
  private String profileContribution;

  public PostResponse(PostDTO postDto, Long postId,String userName) {
    this.id = postId;
    this.userName = userName;
    this.userId = postDto.getUserId();
    this.university = postDto.getUniversity();
    this.title = postDto.getTitle();
    this.content = postDto.getContent();
    this.videoURL = postDto.getVideoURL();
    this.commentsCounts=postDto.getCommentsCounts();
    this.category = postDto.getCategory();
    this.createdDate = postDto.getCreatedDate();
    this.imageURLs = postDto.getImages().stream()
            .map(img -> new ImageOnlyURL(img.getFileName(), img.getImageURL()))
            .collect(Collectors.toList());
    this.tools = postDto.getTools();

    this.viewCount = postDto.getViewCount();
    this.contact = postDto.getContact();
    this.isConfirmed = postDto.getIsConfirmed();
  }




  public PostResponse(PostDTO postDto) {
    this.id = postDto.getId();
//    this.userId = postDto.getUserId();
    this.userName = postDto.getUserName();
    this.title = postDto.getTitle();
    this.content = postDto.getContent();
    this.videoURL = postDto.getVideoURL();
    this.viewCount = postDto.getViewCount();
    this.category = postDto.getCategory();
    this.tools = postDto.getTools();
    this.createdDate = postDto.getCreatedDate();

    this.contact = postDto.getContact();
    this.isConfirmed = postDto.getIsConfirmed();
  }

  public PostResponse(PostDTO postDto, List<ToolDTO> toolDTO, List<Image> images, List<CommentDTO> commentDTO, ProfileDTO profileDTO) {
    this.id = postDto.getId();
    this.userId = postDto.getUserId();
    this.university = postDto.getUniversity();
    this.userName = postDto.getUserName();
    this.title = postDto.getTitle();
    this.content = postDto.getContent();
    this.viewCount = postDto.getViewCount();
    this.category = postDto.getCategory();
    this.commentsCounts=postDto.getCommentsCounts();
    this.tools = postDto.getTools();
    this.videoURL = postDto.getVideoURL();
    this.likeCount = postDto.getLikeCount();
    this.createdDate = postDto.getCreatedDate();
    this.imageURLs = images.stream()
            .map(img -> new ImageOnlyURL(img.getFileName(), img.getImageURL()))
            .collect(Collectors.toList());
    this.contact = postDto.getContact();

    this.profileContact = profileDTO.getContact();
    this.profileInfo = profileDTO.getInfo();
    this.profileName = profileDTO.getName();
    this.profileContribution = profileDTO.getContribution();

    this.isConfirmed = postDto.getIsConfirmed();

    if (this.tools == null) {
      this.tools = new ArrayList<>();
    }
    this.tools.add(toolDTO.get(0).getTools().toString());

    if (commentDTO != null) {
      this.comments = commentDTO.stream()
              .map(CommentDTO::getContent)
              .collect(Collectors.toList());
    }
  }
public PostResponse(PostDTO postDto, List<ToolDTO> toolDTO, List<Image> images, List<CommentDTO> commentDTO) {
      this.id = postDto.getId();
      this.userId = postDto.getUserId();
      this.university = postDto.getUniversity();
      this.userName = postDto.getUserName();
      this.title = postDto.getTitle();
      this.commentsCounts=postDto.getCommentsCounts();
      this.content = postDto.getContent();
      this.viewCount = postDto.getViewCount();
      this.category = postDto.getCategory();
      this.tools = postDto.getTools();
      this.videoURL = postDto.getVideoURL();
      this.likeCount = postDto.getLikeCount();
      this.createdDate = postDto.getCreatedDate();
      this.imageURLs = images.stream()
              .map(img -> new ImageOnlyURL(img.getFileName(), img.getImageURL()))
              .collect(Collectors.toList());
      this.contact = postDto.getContact();

      this.isConfirmed = postDto.getIsConfirmed();

      if (this.tools == null) {
        this.tools = new ArrayList<>();
      }
      this.tools.add(toolDTO.get(0).getTools().toString());

      if (commentDTO != null) {
        this.comments = commentDTO.stream()
                .map(CommentDTO::getContent)
                .collect(Collectors.toList());
      }


  }
}

