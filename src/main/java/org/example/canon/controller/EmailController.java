package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.EmailRequest;
import org.example.canon.controller.response.emailResponse.EmailResponse;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sending")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/{postId}")
    public ResponseEntity<EmailResponse> SendEmail(
            @PathVariable Long postId,
            @AuthenticationPrincipal CustomOAuth2UserDTO userDto){

        System.out.println("==여기까지는 됨 ===");
        emailService.sendMail(userDto,postId);

        return ResponseEntity.ok().build();
    }

}
