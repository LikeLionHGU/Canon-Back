package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.canon.controller.request.PostRequest;
import org.example.canon.dto.PostDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private String university;

    private String content;

    private String title;

    private String category;


    private String imageURL;

    private String videoURL;

    private String fileName;

    private String major;

    private String year;



    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tools> tools = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    private int countLike=0;

    private byte isConfirmed;

    private long commentsCounts=0;


    private String contact;

    private long viewCount=0;
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

    public static Post of(PostDTO dto , User user){ //이미지를 뺌
        return Post.builder()
                .content(dto.getContent())
                .title(dto.getTitle())
                .university(dto.getUniversity())
                .category(dto.getCategory())
                .videoURL(dto.getVideoURL())
                .major(dto.getMajor())
                .year(dto.getYear())
                .contact(dto.getContact())
                .viewCount(dto.getViewCount())
                .user(user)
                .build();
    }

    public void confirmPost(byte decision){
        this.isConfirmed = decision;
    }

    public void updatePostAndFile(PostRequest request, String imageURL, String fileName){

        this.content = request.getContent();
        this.title = request.getTitle();
        this.university = request.getUniversity();
        this.category = request.getCategory();
        this.contact = request.getContact();
        this.major = request.getMajor();
        this.year = request.getYear();
        this.imageURL = imageURL;
        this.fileName = fileName;
    }


    public void updatePostOnly(PostRequest request){
        this.content = request.getContent();
        this.title = request.getTitle();
        this.major = request.getMajor();
        this.university = request.getUniversity();
        this.year = request.getYear();
        this.category = request.getCategory();
        this.contact = request.getContact();
    }


}
