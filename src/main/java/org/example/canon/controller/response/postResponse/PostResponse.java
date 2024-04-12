package org.example.canon.controller.response.postResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.PostDTO;
import org.example.canon.entity.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponse {

  private Long id;
  private Long userId;
  private String title;
  private String content;
  private String category;

  private LocalDateTime createdDate;

  private String imageURL;

  private String contact;

  private byte isComfirmed;


  public PostResponse(PostDTO postDto, Long postId) {
    this.id = postId;
    this.userId = postDto.getUserId();
    this.title = postDto.getTitle();
    this.content = postDto.getContent();
    this.category = postDto.getCategory();
    this.createdDate = postDto.getCreatedDate();
    this.imageURL = postDto.getImageURL();
    this.contact = postDto.getContact();
    this.isComfirmed = postDto.getIsComfirmed();
  }

}

