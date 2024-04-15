package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.canon.dto.CommentDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLike extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static PostLike of(User user, Post post){
        return PostLike.builder()
                .user(user)
                .post(post)
                .build();
    }
}
