package org.example.canon.controller.response.profileResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.ProfileDTO;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileResponse {

    private String name;

    private String info;

    private String contact;

    private String contribution;

    private String profileImageURL;

    public ProfileResponse(ProfileDTO profileDTO) {
            this.name = profileDTO.getName();
            this.info = profileDTO.getInfo();
            this.contact = profileDTO.getContact();
            this.contribution = profileDTO.getContribution();
            this.profileImageURL = profileDTO.getProfileImageURL();

    }
}
