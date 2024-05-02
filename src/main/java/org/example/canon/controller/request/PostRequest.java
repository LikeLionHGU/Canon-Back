package org.example.canon.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostRequest {

    private String category;
    private String contact;
    private String content;
    private String title;
    private List<String> tools;



}
