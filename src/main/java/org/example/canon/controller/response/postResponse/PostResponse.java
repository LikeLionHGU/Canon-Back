package org.example.canon.controller.response.postResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.ToolDTO;
import org.example.canon.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

  private long viewCount;


  public PostResponse(PostDTO postDto, Long postId,String userName) {
    this.id = postId;
    this.userName = userName;
    this.title = postDto.getTitle();
    this.content = postDto.getContent();
    this.category = postDto.getCategory();
    this.createdDate = postDto.getCreatedDate();
    this.imageURL = postDto.getImageURL();
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
    this.viewCount = postDto.getViewCount();
    this.category = postDto.getCategory();
    this.tools = postDto.getTools();
    this.createdDate = postDto.getCreatedDate();
    this.imageURL = postDto.getImageURL();
    this.contact = postDto.getContact();
    this.isConfirmed = postDto.getIsConfirmed();
  }

  public PostResponse(PostDTO postDto, List<ToolDTO> toolDTO) {
    this.id = postDto.getId();
    this.userName = postDto.getUserName();
    this.title = postDto.getTitle();
    this.content = postDto.getContent();
    this.viewCount = postDto.getViewCount();
    this.category = postDto.getCategory();

    this.tools = postDto.getTools();
    this.createdDate = postDto.getCreatedDate();
    this.imageURL = postDto.getImageURL();
    this.contact = postDto.getContact();

    this.isConfirmed = postDto.getIsConfirmed();

    for(int i = 0; i < toolDTO.size(); i++) {
      if (this.tools == null) {
        this.tools = new ArrayList<>();
      }
      this.tools.add(toolDTO.get(i).getTools().toString());
    }

  }
}

