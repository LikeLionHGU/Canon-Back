package org.example.canon.dto;

import lombok.*;
import org.example.canon.entity.User;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {

    private String role;
    private String username;
    private String name;
    private String email;

    public UserDTO(){

    }


    public static UserDTO of(User user){
            return UserDTO.builder()
                    .username(user.getUsername())
                    .name(user.getName())
                    .role(user.getRole())
                    .email(user.getEmail())
                    .build();
    }
}
