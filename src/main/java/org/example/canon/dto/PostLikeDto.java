package org.example.canon.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDto {

    private Long postLikeId;

    private Long postId;

    private String email;
}
