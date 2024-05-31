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

    //얘 외래키로 바꿔야함
    private String sender;

    public static Email of(String receiver, String sender){
        return Email.builder()
                .receiver(receiver)
                .sender(sender)
                .build();
    }

}
