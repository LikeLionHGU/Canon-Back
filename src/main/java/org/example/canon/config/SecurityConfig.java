package org.example.canon.config;

import org.example.canon.customSuccessHandler.CustomSuccessHandler;
import org.example.canon.jwt.JWTFilter;
import org.example.canon.jwt.JWTUtil;
import org.example.canon.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomSuccessHandler customSuccessHandler;
  private final JWTUtil jwtUtil;

  public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil) {

    this.customOAuth2UserService = customOAuth2UserService;
    this.customSuccessHandler = customSuccessHandler;
    this.jwtUtil = jwtUtil;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf((AbstractHttpConfigurer::disable));

    http.formLogin(AbstractHttpConfigurer::disable);

    http.httpBasic((AbstractHttpConfigurer::disable));

      //JWTFilter 추가
      http
              .addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);

    //oauth2
    http
            .oauth2Login((oauth2) -> oauth2
                    .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                            .userService(customOAuth2UserService))
                    .successHandler(customSuccessHandler)
            );

    //경로별 인가 작업
    http
            .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/","*/post/main","posts/filtered","posts/{postId}").permitAll() // 게시글 보는 거 다 가능해야함
                    .requestMatchers("/my","/posts","/comment/*","/sending/*").hasRole("USER")   //User : 댓글, 게시글 작성 , 마이페이지 / Admin : 컨펌하는 페이지
                    .requestMatchers("/admin/*").hasRole("ADMIN")
                    .anyRequest().authenticated());

    //세션 설정 : STATELESS
    http
            .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
