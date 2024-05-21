package org.example.canon.service;
import lombok.RequiredArgsConstructor;
import org.example.canon.entity.Image;
import org.example.canon.entity.Post;
import org.example.canon.repository.ImagesRepository;
import org.example.canon.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagesService {
    private final ImagesRepository imagesRepository;
    private final PostRepository postRepository;

    public void saveImages(Post post, List<Image> images){

        if(images.isEmpty()){
            return;
        }


        for(int i=0; i<images.size() ; i++){
            Image image = Image.of(images.get(i), post);
            imagesRepository.save(image);
        }
    }

    public List<Image> getAllImagesByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        List<Image> images = imagesRepository.findAllByPost(post);
        return images;
    }

}
