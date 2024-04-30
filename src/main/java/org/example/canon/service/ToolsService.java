package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.entity.Post;
import org.example.canon.entity.Tools;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.ToolsRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToolsService {

    private final ToolsRepository toolsRepository;
    private final PostRepository postRepository;

    public void saveTools(Post post, String toolsName){
        if(toolsName.isEmpty()){
            return;
        }

        Tools.of(toolsName,post);

        for(int i = 0; i < toolsName.length()-1; i ++){
//            toolsRepository.save(userId, toolsName);
    }

    //List랑 유저를 인자로 받아서 그 크기만큼 반복
        // List 하나에 있는 거 유저 정보랑 같이 save


}
}
