package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.response.CommentResponse.CommentListResponse;
import org.example.canon.dto.CommentDto;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.dto.PostDTO;
import org.example.canon.entity.Comment;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.example.canon.exception.CommentDeleteDisableException;
import org.example.canon.exception.PostDeleteDisableException;
import org.example.canon.exception.PostNotFoundException;
import org.example.canon.repository.CommentRepository;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment addComment (String email, CommentDto commentDto){
        User user = userRepository.findByEmail(email);
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new NoSuchElementException("No post found with id " + commentDto.getPostId()));;
        Comment comment = Comment.of(commentDto, user, post);
        commentRepository.save(comment);
        return comment;
    }

    public CommentDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(PostNotFoundException::new);
        return CommentDto.of(comment);
    }

    public List<CommentDto> getAllForPost(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().map(CommentDto::of).toList();
    }

    public void deleteComment(Long commentId, CustomOAuth2UserDto userDTO) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (userDTO.getEmail().equals(comment.get().getUser().getEmail())) {
            commentRepository.deleteById(commentId);
        } else {
            throw new CommentDeleteDisableException();
        }
    }
}

