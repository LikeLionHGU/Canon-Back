package org.example.canon.controller.response.profileResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.ProfileDTO;
import org.example.canon.entity.Post;
import org.example.canon.entity.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileResponse {

    private String name;

    private String info;

    private String contact;

    private String contribution;

    private String profileImageURL;

    private List<Post> uploadedPosts;
    private List<Post> likedPosts;

    public ProfileResponse(ProfileDTO profileDTO) {
            this.name = profileDTO.getName();
            this.info = profileDTO.getInfo();
            this.contact = profileDTO.getContact();
            this.contribution = profileDTO.getContribution();
            this.profileImageURL = profileDTO.getProfileImageURL();
              this.uploadedPosts = profileDTO.getUploadedPosts();
              this.likedPosts = profileDTO.getLikedPosts();

    }
}
