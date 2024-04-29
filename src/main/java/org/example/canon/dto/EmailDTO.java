package org.example.canon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.controller.request.EmailRequest;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {
    private String subject;
    private String message;
    private String from;

    public static EmailDTO of(EmailRequest request){

    return EmailDTO.builder()
        .subject(request.getSubject())
        .message(request.getMessage())
        .from(request.getSender())
        .build();
    }

}
