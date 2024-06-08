package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.PostLikeDTO;
import org.example.canon.dto.UserDTO;
import org.example.canon.entity.Post;
import org.example.canon.entity.PostLike;
import org.example.canon.entity.User;
import org.example.canon.exception.LikeDeleteDisableException;
import org.example.canon.exception.UserAlreadyLikedException;
import org.example.canon.repository.PostLikeRepository;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        post.plusLike();
        postRepository.save(post);
        return postLike;
    }

    public Boolean checkUserLikesByPost(Long postId, String email) {
        User findUser = userRepository.findByEmail(email);
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Optional<PostLike> like = postLikeRepository.findByUserAndPost(findUser, findPost);
        return like.isPresent();
    }

    public List<PostLikeDTO> getAllForLikesByUser(Long userId) {
        List<PostLike> likes = postLikeRepository.findAllByUserId(userId);
        return likes.stream().map(PostLikeDTO::of).toList();
    }

    public void deleteLike(Long postId, CustomOAuth2UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("No post found with id " + postId));

        Optional<PostLike> postLike = postLikeRepository.findByUserAndPost(user, post);

        if (postLike.isPresent()) {
            postLikeRepository.deleteById(postLike.get().getPostLikeId());
            post.minusLike();
            postRepository.save(post);
        } else {
            throw new LikeDeleteDisableException();
        }
    }

}
