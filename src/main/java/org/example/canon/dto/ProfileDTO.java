package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.ProfileRequest;
import org.example.canon.entity.Post;
import org.example.canon.entity.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {

    private String name;

    private String info;
    private Long userId;

    private String contact;
    private String contribution;


    private List<PostDTO2> uploadedPosts;
    private List<PostDTO2> likedPosts;

    private String role;

    public static ProfileDTO of(ProfileRequest profileRequest){

        return ProfileDTO.builder()
                .name(profileRequest.getName())
                .info(profileRequest.getInfo())
                .contribution(profileRequest.getContribution())
                .contact(profileRequest.getContact())
                .build();
    }

    public static ProfileDTO of(Profile profile, List<PostDTO2> uploadedPosts, List<PostDTO2> likedPosts) {


        return ProfileDTO.builder()
                .name(profile.getName())
                .userId(profile.getId())
                .info(profile.getInfo())
                .contribution(profile.getContribution())
                .contact(profile.getContact())
                .uploadedPosts(uploadedPosts)
                .likedPosts(likedPosts)
                .build();
    }

    public static ProfileDTO of(Profile profile, List<PostDTO2> uploadedPosts, List<PostDTO2> likedPosts,String role) {


        return ProfileDTO.builder()
                .name(profile.getName())
                .userId(profile.getId())
                .info(profile.getInfo())
                .contribution(profile.getContribution())
                .contact(profile.getContact())
                .uploadedPosts(uploadedPosts)
                .likedPosts(likedPosts)
                .role(role)
                .build();
    }

    public static ProfileDTO of(Profile profile){
        return ProfileDTO.builder()
                .name(profile.getName())
                .userId(profile.getId())
                .info(profile.getInfo())
                .contribution(profile.getContribution())
                .contact(profile.getContact())
                .build();
    }

    // @Builder(builderMethodName = "childBuilder", buildMethodName = "buildChild", setterPrefix = "with", toBuilder = true
}
