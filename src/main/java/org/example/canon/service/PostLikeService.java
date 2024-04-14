package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.dto.CommentDto;
import org.example.canon.entity.Comment;
import org.example.canon.entity.Post;
import org.example.canon.entity.PostLike;
import org.example.canon.entity.User;
import org.example.canon.exception.UserAlreadyLikedException;
import org.example.canon.repository.CommentRepository;
import org.example.canon.repository.PostLikeRepository;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostLike addLike(String email, Long postId) {
        User user = userRepository.findByEmail(email);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("No post found with id " + postId));
        if (postLikeRepository.findByUserAndPost(user, post).isPresent()) {
            throw new UserAlreadyLikedException();
        }
        PostLike postLike = PostLike.of(user, post);
        postLikeRepository.save(postLike);
        return postLike;
    }

}
