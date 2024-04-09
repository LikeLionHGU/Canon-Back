package org.example.canon.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.canon.dto.PostDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;
    private String fileUrl; //파일의 url

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public static Post toPost(PostDto postDto, User user) { //레포지토리에 올리기 전 서비스에서 레포 올리는 형식으로 변환
        return Post.builder()
                .title(postDto.getTitle())
                .fileUrl(postDto.getFileUrl())
                .content(postDto.getContent())
                .user(user)
                .build();
    }


}
