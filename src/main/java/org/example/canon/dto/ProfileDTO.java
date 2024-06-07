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


    private List<PostDTO> uploadedPosts;
    private List<PostDTO> likedPosts;

    public static ProfileDTO of(ProfileRequest profileRequest){

        return ProfileDTO.builder()
                .name(profileRequest.getName())
                .info(profileRequest.getInfo())
                .contribution(profileRequest.getContribution())
                .contact(profileRequest.getContact())
                .build();
    }

    public static ProfileDTO of(Profile profile, List<PostDTO> uploadedPosts, List<PostDTO> likedPosts) {


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
