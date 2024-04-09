package org.example.canon.repository;
import java.util.List;

import org.example.canon.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.user order by p.createdDate desc")
    List<Post> findAllWithMember(Pageable pageable);
}
