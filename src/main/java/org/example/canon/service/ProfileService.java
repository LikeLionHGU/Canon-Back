package org.example.canon.service;

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

        //userDto에 담긴 정보로 유저 찾기
        //이미 존재하면 업데이트

        //존재하지 않으면 새로 추가

    }

     public ProfileDTO getProfile(CustomOAuth2UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());
        Profile profile = profileRepository.findByUser(user);
        return ProfileDTO.of(profile);
    }


}
