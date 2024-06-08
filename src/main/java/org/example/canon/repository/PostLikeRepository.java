package org.example.canon.repository;



import org.example.canon.entity.Comment;
import org.example.canon.entity.Post;
import org.example.canon.entity.PostLike;
import org.example.canon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    @Query("SELECT pl FROM PostLike pl WHERE pl.user = :user AND pl.post = :post")
    Optional<PostLike> findByUserAndPost(User user, Post post);

    List<PostLike> findAllByPostId(Long postId);

    List<PostLike> findAllByUserId(Long userId);
    void deleteAllByPostId(Long postId);
}

