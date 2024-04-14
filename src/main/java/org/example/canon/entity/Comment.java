package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.canon.dto.CommentDto;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public static Comment of(CommentDto dto , User user, Post post){
        return Comment.builder()
                .content(dto.getContent())
                .user(user)
                .post(post)
                .build();
    }
}
