package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.ProfileRequest;
import org.example.canon.entity.Profile;

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

    private String profileImageURL;

    public static ProfileDTO of(ProfileRequest profileRequest , String profileImageURL){

        return ProfileDTO.builder()
                .name(profileRequest.getName())
                .info(profileRequest.getInfo())
                .contribution(profileRequest.getContribution())
                .contact(profileRequest.getContact())
                .profileImageURL(profileImageURL)
                .build();
    }

    public static ProfileDTO of(Profile profile){
        return ProfileDTO.builder()
                .name(profile.getName())
                .userId(profile.getId())
                .info(profile.getInfo())
                .contribution(profile.getContribution())
                .contact(profile.getContact())
                .profileImageURL(profile.getProfileImageURL())
                .build();
    }

    // @Builder(builderMethodName = "childBuilder", buildMethodName = "buildChild", setterPrefix = "with", toBuilder = true
}
