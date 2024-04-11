package org.example.canon.repository;


import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {


}
