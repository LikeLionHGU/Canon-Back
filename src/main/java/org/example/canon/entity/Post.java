package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends Base {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String title;

    private byte isComfirmed;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User")
    private User user;



}
