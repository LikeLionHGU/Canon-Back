package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.ProfileDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String info;

    private String contribution;

    private String contact;

    private String profileImageURL;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User user;

    public static Profile of(ProfileDTO profileDTO,User user){

        return Profile.builder()
                .name(profileDTO.getName())
                .info(profileDTO.getInfo())
                .contribution(profileDTO.getContribution())
                .contact(profileDTO.getContact())
                .profileImageURL(profileDTO.getProfileImageURL())
                .user(user)
                .build();
    }

    public void update(ProfileDTO profileDTO){
        this.name = profileDTO.getName();
        this.info = profileDTO.getInfo();
        this.contribution = profileDTO.getContribution();
        this.contact = profileDTO.getContact();
        this.profileImageURL = profileDTO.getProfileImageURL();
    }

    public static Profile of(User user){

        return Profile.builder()
                    .user(user)
                    .name(user.getUsername())
                    .profileImageURL(null)
                .contact(user.getEmail())
                .profileImageURL(null)
                .contribution(null)
                .info(null)
                .build();
    }

}
