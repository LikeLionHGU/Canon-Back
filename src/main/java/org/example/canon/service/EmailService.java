package org.example.canon.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.canon.repository.EmailRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;


}
