package org.example.canon.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {

    private String subject;
    private String message;
    private String sender;

}
