package org.example.canon.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.canon.controller.response.loginResponse.LoginResponse;
import org.example.canon.repository.UserRepository;
import org.example.canon.service.CustomOAuth2UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final CustomOAuth2UserService customOAuth2UserService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginWithGoogle(@AuthenticationPrincipal OAuth2User user) {


        LoginResponse loginResponse = new LoginResponse(user);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authentication")) {
                    cookie.setValue("");
                    cookie.setMaxAge(0); // 브라우저 종료 시 쿠키 삭제
                    cookie.setPath("/"); // 전체 경로에서 쿠키 삭제
                    response.addCookie(cookie);
                }
            }
        }
        return "로그아웃 되었습니다."; // 로그아웃 후 리디렉션 할 페이지
    }


}
