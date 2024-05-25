package org.example.canon.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.ProfileRequest;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.ProfileDTO;
import org.example.canon.entity.Profile;
import org.example.canon.entity.User;
import org.example.canon.repository.ProfileRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

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
        return ProfileDTO.of(profile);
    }


}
