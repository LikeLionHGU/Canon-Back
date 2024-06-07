package org.example.canon.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.controller.response.loginResponse.GoogleResponse;
// import org.example.canon.dto.NaverResponse;
import org.example.canon.dto.OAuth2Response;
import org.example.canon.dto.UserDTO;
import org.example.canon.entity.Profile;
import org.example.canon.entity.User;
import org.example.canon.repository.ProfileRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;

  public CustomOAuth2UserService(UserRepository userRepository, ProfileRepository profileRepository) {
    this.userRepository = userRepository;
      this.profileRepository = profileRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    List<String> admin = new ArrayList<>(Arrays.asList("22000082@handong.ac.kr", "22100595@handong.ac.kr", "lucas0606@handong.ac.kr", "shalom99904@gmail.com", "xofks136@handong.ac.kr"));
    OAuth2User oAuth2User = super.loadUser(userRequest);
    System.out.println(oAuth2User.getAttributes());

    OAuth2Response oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

    String name = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

    User existData = userRepository.findByEmail(oAuth2Response.getEmail());

    String role = null;

    if (existData == null) {
      User userEntity = new User();
      userEntity.setUsername(oAuth2Response.getName());
      userEntity.setEmail(oAuth2Response.getEmail());
      if (admin.contains(oAuth2Response.getEmail())) {
        userEntity.setRole("ADMIN");
      } else {
        userEntity.setRole("USER");
      }
      userEntity.setName(name);
      userRepository.save(userEntity);

      Profile profile = Profile.of(userEntity);

      profileRepository.save(profile);

      UserDTO userDTO = new UserDTO();
      userDTO.setName(name);
      userDTO.setUsername(oAuth2Response.getName());
      userDTO.setEmail(oAuth2Response.getEmail());
      if (admin.contains(oAuth2Response.getEmail())) {
        userDTO.setRole("ADMIN");
      } else {
        userDTO.setRole("USER");
      }

      return new CustomOAuth2UserDTO(userDTO);

    } else {

      existData.setUsername(oAuth2Response.getName());
      existData.setEmail(oAuth2Response.getEmail());
      existData.setName(oAuth2Response.getName());

      role = existData.getRole();

//      userRepository.save(existData);

      UserDTO userDTO = new UserDTO();
      userDTO.setUsername(existData.getUsername());
      userDTO.setRole(existData.getRole());
      userDTO.setName(existData.getName());
      userDTO.setEmail(existData.getEmail());

      return new CustomOAuth2UserDTO(userDTO);

    }
  }
}
