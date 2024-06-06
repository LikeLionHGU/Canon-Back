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
                "    <div style=\"position: absolute; top: 10px; left: 10px; background-color: #FF7D04; color: white; padding: 10px 15px; border-radius: 5px; font-size: 18px; font-weight: bold;\">HUP</div>\n" +
                "    <h1 style=\"font-size: 36px; color: #FF7D04; text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2); margin-bottom: 10px;\">\n" +
                "      Coffee chat\n" +
                "    </h1>\n" +
                "    <hr style=\"border: none; border-top: 2px solid #FF7D04; width: 80%; margin: 20px auto;\">\n" +
                "    <p style=\"font-size: 18px; line-height: 1.5; margin-bottom: 20px;\">안녕하세요 [ "+ receiverMail +" ]님,<br>\n" +
                "      [HUP] 플랫폼을 통해 [ "+ receiverMail +" ]님께 커피챗 요청이 도착했습니다.<br>\n" +
                "      [ "+ sender +" ]님은 [ " + receiverMail +" ]님의 졸업 작품에 큰 관심을 가지고 있으며,<br>\n" +
                "      이 기회를 통해 졸업 작품에 대한 피드백을 얻거나, 전문 지식을 공유하고,<br>\n" +
                "      창의적인 아이디어를 나누게 될 수 있습니다.<br>\n" +
                "      커피챗에 참여하여 유익한 대화를 나누고 싶다면<br>\n" +
                "      아래 메일을 통해 대화를 시작해보세요!<br>\n" +
                "      감사합니다.<br>\n" +
                "      [HUP] 팀\n" +
                "    </p>\n" +
                "    <p style=\"font-size: 18px; line-height: 1.5; margin-bottom: 20px;\">연락처 : " + sender + "</p>\n" +
                "    <hr style=\"border: none; border-top: 2px solid #FF7D04; width: 80%; margin: 20px auto;\">\n" +
                "    <p style=\"font-size: 14px; color: #666; margin-bottom: 0;\">답장은 위 연락처로 부탁드립니다.</p>\n" +
                "    <div style=\"margin-top: 20px; display: flex; justify-content: center;\">\n" +
                "      <div style=\"display: flex; justify-content: center; flex-wrap: wrap;\">\n" +
                "        <a href=\"#\" style=\"display: inline-block; background-color: #FF7D04; color: white; text-decoration: none; padding: 12px 24px; border-radius: 5px; margin: 10px; font-weight: bold; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); transition: all 0.3s ease;\">웹사이트</a>\n" +
                "        <a href=\"#\" style=\"display: inline-block; background-color: #333; color: white; text-decoration: none; padding: 12px 24px; border-radius: 5px; margin: 10px; font-weight: bold; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); transition: all 0.3s ease;\">문의</a>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";


        String title = "[HUP] 커피챗 요청 from "+sender;
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject(title);
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
