package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.dto.PostDTO;
import org.example.canon.entity.Post;
import org.example.canon.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PostService {


    private final PostRepository postRepository;

        public void save(PostDTO postDTO) {
            Post post = Post.of(postDTO);
            postRepository.save(post);
        }

        public List<Post> getAllPost() {
            return postRepository.findAll();
        }

        public Post getPost(Long postid) {
            return postRepository.findById(postid).orElse(null);
        }

    }

