package org.example.canon.controller.response.postResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.PostDTO;
import org.example.canon.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponse {

  private Long id;
//  private Long userId;

  private String userName;
  private String title;
  private String content;
  private String category;

  private LocalDateTime createdDate;

  private String imageURL;

  private String contact;

  private byte isConfirmed;
  private List<String> tools;


  public PostResponse(PostDTO postDto, Long postId,String userName) {
    this.id = postId;
    this.userName = userName;
    this.title = postDto.getTitle();
    this.content = postDto.getContent();
    this.category = postDto.getCategory();
    this.createdDate = postDto.getCreatedDate();
    this.imageURL = postDto.getImageURL();
    this.tools = postDto.getTools();
    this.contact = postDto.getContact();
    this.isConfirmed = postDto.getIsConfirmed();
  }

  public PostResponse(PostDTO postDto) {
    this.id = postDto.getId();
//    this.userId = postDto.getUserId();
    this.userName = postDto.getUserName();
    this.title = postDto.getTitle();
    this.content = postDto.getContent();
    this.category = postDto.getCategory();
    this.tools = postDto.getTools();
    this.createdDate = postDto.getCreatedDate();
    this.imageURL = postDto.getImageURL();
    this.contact = postDto.getContact();
    this.isConfirmed = postDto.getIsConfirmed();
  }
}

