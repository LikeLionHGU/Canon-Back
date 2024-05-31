package org.example.canon.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PostRequest {

    private String category;
    private String university;
    private String contact;
    private String content;
    private String major;
    private String year;
    private String title;
    private String videoURL;
    private List<String> tools;



}
