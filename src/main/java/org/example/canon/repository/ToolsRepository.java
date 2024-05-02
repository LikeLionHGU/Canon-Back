package org.example.canon.repository;


import org.example.canon.entity.Post;
import org.example.canon.entity.PostLike;
import org.example.canon.entity.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolsRepository extends JpaRepository<Tools, Long> {
    List<Tools> findAllByPost(Post post);
}
