package org.example.canon.repository;


import org.example.canon.dto.PostDTO;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {

    @Query("Select p from Post p where p.isConfirmed=0")
    List<Post> findAllByIsNotChecked(); // 이건 admin용

    @Query("Select p from Post p where p.isConfirmed=2")
    List<Post> findAllByIsDenied(); //admin용

    @Query("Select p from Post p where p.isConfirmed=1")
    List<Post> findAllByConfirmed(); // 이건 user용

    List<Post> findAllByUserId(Long userId); //마이페이지용

    @Query("SELECT p FROM Post p WHERE p.user.email = :email")
    List<Post> findAllByUserEmail(@Param("email") String email);


    List<Post> findAll(Specification<Post> spec);
}
