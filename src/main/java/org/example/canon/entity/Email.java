package org.example.canon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.example.canon.controller.request.EmailRequest;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //얘 외래키로 바꿔야함
    private String receiver;
    private String message;
    private String subject;

    //얘 외래키로 바꿔야함
    private String sender;

    public static Email of(EmailRequest emailRequest, String to){
        return Email.builder()
                .receiver(to)
                .subject(emailRequest.getSubject())
                .message(emailRequest.getMessage())
                .sender(emailRequest.getSender())
                .build();
    }

}
