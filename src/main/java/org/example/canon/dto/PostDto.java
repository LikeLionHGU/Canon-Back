package org.example.canon.dto;



import java.time.LocalDateTime;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    private String fileUrl; //파일의 url

    public static PostDto from(Post post) {
        return PostDto.builder()
                .postId(post.getPostId())
                .userId(post.getUser().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .fileUrl(post.getFileUrl())
                .build();
    }

    public static PostDto from(String filePath, PostRequest postForm) { //post 할때에 받은 값을 db에 넣는것으로 변환하는 용도
        return PostDto.builder()
                .title(postForm.getTitle())
                .content(postForm.getContent())
                .userId(postForm.getMemberId())
                .fileUrl(filePath)
                .build();
    }
}