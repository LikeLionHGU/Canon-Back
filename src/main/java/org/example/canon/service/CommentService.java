package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.dto.CommentDTO;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.entity.Comment;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.example.canon.exception.CommentDeleteDisableException;
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

    public Comment addComment (String email, CommentDTO commentDto){
        User user = userRepository.findByEmail(email);
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new NoSuchElementException("No post found with id " + commentDto.getPostId()));;
        Comment comment = Comment.of(commentDto, user, post);
        commentRepository.save(comment);
        return comment;
    }

    public CommentDTO getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(PostNotFoundException::new);
        return CommentDTO.of(comment);
    }

    public List<CommentDTO> getAllForPost(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().map(CommentDTO::of).toList();
    }

    public void deleteComment(Long commentId, CustomOAuth2UserDTO userDTO) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (userDTO.getEmail().equals(comment.get().getUser().getEmail())) {
            commentRepository.deleteById(commentId);
        } else {
            throw new CommentDeleteDisableException();
        }
    }
}

