package org.example.canon.controller.response.EmailResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class EmailResponse {

    private String to;
    private String message;
    private String from;

}
