package org.example.canon.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.example.canon.dto.PostDTO;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


@Service
@RequiredArgsConstructor

public class PostService {



    private final PostRepository postRepository;
    private final UserRepository userRepository;
        public long save(PostDTO postDTO,String email) {
            System.out.println(email);
            User user = userRepository.findByEmail(email);

            Post post = Post.of(postDTO,user);
            postRepository.save(post);

            return post.getId();
        }

        public List<Post> getAllPost() {
            return postRepository.findAll();
        }

        public Post getPost(Long postId) {
            return postRepository.findById(postId).orElse(null);
        }

    }

