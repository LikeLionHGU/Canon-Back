package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.EmailRequest;
import org.example.canon.controller.response.EmailResponse.EmailResponse;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sending")
@CrossOrigin("*")
public class EmailController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmailResponse> SendingEmail(
            @RequestBody EmailRequest request,
            @AuthenticationPrincipal CustomOAuth2UserDTO userDto){


    }

}
