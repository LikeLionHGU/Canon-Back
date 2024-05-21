package org.example.canon.repository;


import org.example.canon.entity.Image;
import org.example.canon.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByPost(Post post);
    void deleteAllByPostId(Long postId);
}
