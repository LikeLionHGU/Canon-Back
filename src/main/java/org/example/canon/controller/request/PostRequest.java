package org.example.canon.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class PostRequest {

    private Long memberId;
    private String title;
    private String content;


}
