package org.example.canon.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {


    private Long userId;

    private String content;

    private String title;

    private byte isComfirmed;




}
