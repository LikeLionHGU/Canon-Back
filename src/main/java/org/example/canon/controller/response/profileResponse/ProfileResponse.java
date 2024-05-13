package org.example.canon.controller.response.profileResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileResponse {

    private String name;

    private String info;

    private String contact;

    private String contribution;

    private String profileImageURL;

    private Long userId;
}
