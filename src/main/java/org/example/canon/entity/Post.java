package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.dto.PostDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends Base {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String title;

    private String category;

    private String imageURL;

    private String fileName;

    private int countLike;

    private byte isConfirmed;

    private String contact;

    private long viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User user;

    public void plusLike() {
        this.countLike += 1;
    }

    public void minusLike() {
        this.countLike -= 1;
    }

    public void plusViewCount() {
        this.viewCount += 1;
    }

    public static Post of(PostDTO dto , User user){
        return Post.builder()
                .content(dto.getContent())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .imageURL(dto.getImageURL())
                .contact(dto.getContact())
                .viewCount(dto.getViewCount())
                .fileName(dto.getFileName())
                .user(user)
                .build();
    }

    public void confirmPost(byte decision){
    this.isConfirmed = decision;
     }

    public void updatePostAndFile(PostRequest request, String imageURL, String fileName){

        this.content = request.getContent();
        this.title = request.getTitle();
        this.category = request.getCategory();
        this.contact = request.getContact();
        this.imageURL = imageURL;
        this.fileName = fileName;
    }


    public void updatePostOnly(PostRequest request){
        this.content = request.getContent();
        this.title = request.getTitle();
        this.category = request.getCategory();
        this.contact = request.getContact();
    }


}
