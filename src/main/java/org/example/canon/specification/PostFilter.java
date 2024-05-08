package org.example.canon.specification;

import org.example.canon.entity.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostFilter {

    public static Specification<Post> filterBy(String author) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author"), author);
    }
}
