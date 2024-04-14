package org.example.canon.repository;


import org.example.canon.entity.Comment;
import org.example.canon.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId); //게시물 별 댓글 찾기

}

