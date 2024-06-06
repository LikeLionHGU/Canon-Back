package org.example.canon.customSuccessHandler;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.jwt.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2UserDTO customUserDetails = (CustomOAuth2UserDTO) authentication.getPrincipal();

        String username = customUserDetails.getUsername();
        System.out.println("=======");
        System.out.println(username);


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        System.out.println("=======");
        String email = customUserDetails.getEmail();

        System.out.println(email);
        System.out.println("=======");
        System.out.println(role);

        String token = jwtUtil.createJwt(username, email,role, 1000 * 60 * 60 * 24 * 30L);

        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("http://localhost:3000/DaePo");
    }

    public Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60*60);
//        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
//        cookie.setDomain("localhost");
//        cookie.setAttribute("SameSite", "None");
        return cookie;
    }
}
