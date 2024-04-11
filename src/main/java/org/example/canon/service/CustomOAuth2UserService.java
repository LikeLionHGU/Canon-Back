package org.example.canon.service;

import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.controller.response.GoogleResponse;
// import org.example.canon.dto.NaverResponse;
import org.example.canon.dto.OAuth2Response;
import org.example.canon.dto.UserDTO;
import org.example.canon.entity.User;
import org.example.canon.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  public CustomOAuth2UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);
    System.out.println(oAuth2User.getAttributes());

    OAuth2Response oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

    String name = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

    User existData = userRepository.findByUsername(name);

    String role = null;

    if (existData == null) {
      User userEntity = new User();
      userEntity.setUsername(oAuth2Response.getName());
      userEntity.setEmail(oAuth2Response.getEmail());
      userEntity.setRole("USER");
      userEntity.setName(name);
      userRepository.save(userEntity);

      UserDTO userDTO = new UserDTO();
      userDTO.setName(name);
      userDTO.setUsername(oAuth2Response.getName());
      userDTO.setRole("USER");

      return new CustomOAuth2UserDto(userDTO);

    } else {

      existData.setUsername(oAuth2Response.getName());
      existData.setEmail(oAuth2Response.getEmail());
      existData.setName(oAuth2Response.getName());

      role = existData.getRole();

      userRepository.save(existData);

      UserDTO userDTO = new UserDTO();
      userDTO.setUsername(existData.getUsername());
      userDTO.setRole(existData.getRole());
      userDTO.setName(existData.getName());

      return new CustomOAuth2UserDto(userDTO);

    }
  }
}
