package org.example.canon.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.EmailRequest;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.EmailDTO;
import org.example.canon.dto.UserDTO;
import org.example.canon.entity.Email;
import org.example.canon.entity.Post;
import org.example.canon.entity.User;
import org.example.canon.repository.EmailRepository;
import org.example.canon.repository.PostRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;
    private final PostRepository postRepository;


    public void sendMail(CustomOAuth2UserDTO userDTO, EmailRequest request , Long postId){

        Optional<Post> post = postRepository.findById(postId);
        String receiverMail = post.get().getUser().getEmail();
        EmailDTO dto = EmailDTO.of(request);
        System.out.println("받는 사람 : "+receiverMail);
        System.out.println("보내는 사람 : "+userDTO.getEmail());
        System.out.println("제목 : "+dto.getSubject());
        System.out.println("내용 :"+dto.getMessage());

        SimpleMailMessage msg = new SimpleMailMessage();

        System.out.println("받는 사람 : "+receiverMail);
        msg.setTo(receiverMail);

        System.out.println("보내는 사람 : "+userDTO.getEmail());
        msg.setFrom(userDTO.getEmail());

        System.out.println("제목 : "+dto.getSubject());
        msg.setSubject(dto.getSubject());

        System.out.println("내용 :"+dto.getMessage());
        msg.setText(dto.getMessage());

        javaMailSender.send(msg);

        emailRepository.save(Email.of(request,receiverMail));

    }
}
