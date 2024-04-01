package org.example.canon.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.controller.response.GoogleResponse;
//import org.example.canon.dto.NaverResponse;
import org.example.canon.dto.OAuth2Response;
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

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        
        if (registrationId.equals("naver")) {

//            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        String idCode = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();

        User existData = userRepository.findByUsername(idCode);

        String role = null;

        if (existData == null) {

            User userEntity = new User();
            userEntity.setUsername(oAuth2Response.getName());
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setRole("ROLE_USER");
            userEntity.setIdCode(idCode);

            userRepository.save(userEntity);
        }
        else {

      existData.setUsername(oAuth2Response.getName());
            existData.setEmail(oAuth2Response.getEmail());
            existData.setIdCode(oAuth2Response.getName());

            role = existData.getRole();

            userRepository.save(existData);
        }

        return new CustomOAuth2UserDto(oAuth2Response, role);
    }
}