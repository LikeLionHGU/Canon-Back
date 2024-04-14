package org.example.canon.repository;



import org.example.canon.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {


}

