package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.LoginRequest;
import org.example.canon.controller.response.GoogleResponse;
import org.example.canon.controller.response.LoginResponse;
import org.example.canon.dto.CustomOAuth2UserDto;
import org.example.canon.dto.OAuth2Response;
import org.example.canon.repository.UserRepository;
import org.example.canon.service.CustomOAuth2UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final CustomOAuth2UserService customOAuth2UserService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginWithGoogle(@AuthenticationPrincipal OAuth2User user) {


        LoginResponse loginResponse = new LoginResponse(user);
        return ResponseEntity.ok(loginResponse);
        // return값을 redirect로
    }


}
