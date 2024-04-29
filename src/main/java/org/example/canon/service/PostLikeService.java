package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.PostLikeDTO;
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

    public List<PostLikeDTO> getAllForLikesByPost(Long postId) {
        List<PostLike> likes = postLikeRepository.findAllByPostId(postId);
        return likes.stream().map(PostLikeDTO::of).toList();
    }

    public List<PostLikeDTO> getAllForLikesByUser(Long userId) {
        List<PostLike> likes = postLikeRepository.findAllByUserId(userId);
        return likes.stream().map(PostLikeDTO::of).toList();
    }

    public void deleteLike(Long likeId, CustomOAuth2UserDTO userDTO) {
        Optional<PostLike> postLike = postLikeRepository.findById(likeId);
        Post post = postLike.get().getPost();
        if (userDTO.getEmail().equals(postLike.get().getUser().getEmail())) {
            postLikeRepository.deleteById(likeId);
            post.minusLike();
            postRepository.save(post);
        } else {
            throw new LikeDeleteDisableException();
        }
    }

}
