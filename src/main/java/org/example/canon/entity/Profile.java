package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String info;

    private String contribution;

    private String contact;

    private String profileImageURL;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User user;


}
