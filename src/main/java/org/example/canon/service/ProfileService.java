package org.example.canon.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.ProfileRequest;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.ProfileDTO;
import org.example.canon.entity.Image;
import org.example.canon.entity.Post;
import org.example.canon.entity.Profile;
import org.example.canon.entity.User;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.ProfileRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImagesService imagesService;

    public void addProfile(ProfileDTO profileDTO, CustomOAuth2UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());


            Profile profile = Profile.of(profileDTO,user);
            user.doneProfile();
            profileRepository.save(profile);
        }

    @Transactional
    public void updateProfile(ProfileDTO profileDTO, CustomOAuth2UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());
        Profile profile = profileRepository.findByUser(user);
        profile.update(profileDTO);
    }


     public ProfileDTO getProfile(CustomOAuth2UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());

        Profile profile = profileRepository.findByUser(user);
//         if(profile == null){
//             profile.setName(user.getName());
//             profile.setContact(user.getEmail());
//         }
         System.out.println(user.getEmail());
         List<PostDTO> returnPostsByUser = new ArrayList<>();
         List<PostDTO> returnPostsByLike = new ArrayList<>();
        List<PostDTO> uploadedPosts = postRepository.findAllByUser(user)
                .stream()
                .map(PostDTO::of)
                .toList();

         for (PostDTO nPost : uploadedPosts) {
             List<Image> images = imagesService.getAllImagesByPostId(nPost.getId());

             returnPostsByUser.add(PostDTO.of(nPost, images));
         }

        List<PostDTO> likedPosts = postRepository.findLikedPostsByUser(user.getId())
                .stream()
                .map(PostDTO::of)
                .toList();

         for (PostDTO nPost : likedPosts) {
             List<Image> images = imagesService.getAllImagesByPostId(nPost.getId());

             returnPostsByLike.add(PostDTO.of(nPost, images));
         }

        return ProfileDTO.of(profile,returnPostsByUser,returnPostsByLike);
    }

    public ProfileDTO getProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        Profile profile = profileRepository.findByUser(user);

        if(profile == null){
            profile.setName(user.getName());
            profile.setContact(user.getEmail());
        }

        List<PostDTO> returnPostsByUser = new ArrayList<>();
        List<PostDTO> returnPostsByLike = new ArrayList<>();

        List<PostDTO> uploadedPosts = postRepository.findAllByUser(user)
                .stream()
                .map(PostDTO::of)
                .toList();

        for (PostDTO nPost : uploadedPosts) {
            List<Image> images = imagesService.getAllImagesByPostId(nPost.getId());

            returnPostsByUser.add(PostDTO.of(nPost, images));
        }
        List<PostDTO> likedPosts = postRepository.findLikedPostsByUser(user.getId())
                .stream()
                .map(PostDTO::of)
                .toList();

        for (PostDTO nPost : likedPosts) {
            List<Image> images = imagesService.getAllImagesByPostId(nPost.getId());

            returnPostsByLike.add(PostDTO.of(nPost, images));
        }
        return ProfileDTO.of(profile,uploadedPosts,likedPosts);
    }

    public ProfileDTO getProfileForPost(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        Profile profile = profileRepository.findByUser(user);

        return ProfileDTO.of(profile);
    }

    public boolean hasProfile(Long postId) {
         Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
         User user = post.getUser();
         if(user.getHasProfile() == 0){
             return false;
         }else{
             return true;
         }

    }


}
