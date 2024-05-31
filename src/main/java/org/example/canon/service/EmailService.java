package org.example.canon.service;

import jakarta.mail.internet.MimeMessage;
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
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;
    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;
    private final PostRepository postRepository;


    public void sendMail(CustomOAuth2UserDTO userDTO,  Long postId){
        Optional<Post> post = postRepository.findById(postId);
        String receiverMail = post.get().getContact();
        String sender = userDTO.getEmail();

        System.out.println(receiverMail);
        System.out.println(sender);
        MimeMessage message = mailSender.createMimeMessage();

        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body style=\"background-color: #FF7D04; padding: 20px; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "  <div style=\"background-color: #FFF9EC; padding: 30px; border-radius: 15px; max-width: 650px; margin: 0 auto; text-align: center; color: #333; box-shadow: 0 0 15px rgba(0, 0, 0, 0.2); position: relative;\">\n" +
                "    <h1 style=\"font-size: 36px; color: #FF7D04; text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2); margin-bottom: 10px;\">\n" +
                "      Coffee chat\n" +
                "    </h1>\n" +
                "    <hr style=\"border: none; border-top: 2px solid #FF7D04; width: 80%; margin: 20px auto;\">\n" +
                "    <p style=\"font-size: 18px; line-height: 1.5; margin-bottom: 20px;\">메시지 : 안녕하세요 작품보고 연락드립니다, 확인하시면 답장 부탁드립니다.</p>\n" +
                "    <p style=\"font-size: 18px; line-height: 1.5; margin-bottom: 20px;\">연락처 :" +sender+"</p>\n" +
                "    <hr style=\"border: none; border-top: 2px solid #FF7D04; width: 80%; margin: 20px auto;\">\n" +
                "    <p style=\"font-size: 14px; color: #666; margin-bottom: 0;\">답장은 위 연락처로 부탁드립니다.</p>\n" +
                "    <div style=\"margin-top: 20px; display: flex; justify-content: center;\">\n" +
                "      <a href=\"#\" style=\"display: inline-block; background-color: #FF7D04; color: white; text-decoration: none; padding: 12px 24px; border-radius: 5px; margin: 0 10px; font-weight: bold; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); transition: all 0.3s ease;\">웹사이트 방문</a>\n" +
                "      <a href=\"#\" style=\"display: inline-block; background-color: #333; color: white; text-decoration: none; padding: 12px 24px; border-radius: 5px; margin: 0 10px; font-weight: bold; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); transition: all 0.3s ease;\">문의하기</a>\n" +
                "    </div>\n" +
                "    <div style=\"position: absolute; top: 20px; left: 20px; background-color: #FF7D04; color: white; padding: 15px 25px; border-radius: 5px; font-size: 24px; font-weight: bold;\">HUP</div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject("[HUP] 커피챗 신청 메일입니다.");
            messageHelper.setTo(receiverMail);
            messageHelper.setFrom("gurdl2384@naver.com", "HUP 커피챗 서비스");
            messageHelper.setText(html,true);
            mailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }



        emailRepository.save(Email.of(receiverMail,sender));

    }
}
