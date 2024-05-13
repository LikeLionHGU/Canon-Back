package org.example.canon.dto;

import lombok.*;
import org.example.canon.controller.request.ProfileRequest;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {

    private String name;

    private String info;

    private String contact;

    private String profileImageURL;

    public static ProfileDTO of(ProfileRequest profileRequest , String profileImageURL){

        return ProfileDTO.builder()
                .name(profileRequest.getName())
                .info(profileRequest.getInfo())
                .contact(profileRequest.getContact())
                .profileImageURL(profileImageURL)
                .build();
    }
}
