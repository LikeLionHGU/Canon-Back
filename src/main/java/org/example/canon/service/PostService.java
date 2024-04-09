package org.example.canon.service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.canon.dto.PostDto;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.example.canon.exception.PostNotFoundException;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.example.canon.exception.MemberNotFoundException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long addPost(PostDto postDto) {
        User user =
                userRepository.findById(postDto.getUserId()).orElseThrow(MemberNotFoundException::new);
        Post post = postRepository.save(Post.toPost(postDto, user));
        return post.getPostId();
    }


    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return PostDto.from(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public long countAllPosts() {
        return postRepository.count();
    }

    //아직 안씀
    public List<PostDto> getAllPosts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        List<Post> posts = postRepository.findAllWithMember(pageable);
        return posts.stream().map(PostDto::from).toList();
    }
}
