package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.entity.Post;
import org.example.canon.entity.Tools;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.ToolsRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolsService {

    private final ToolsRepository toolsRepository;
    private final PostRepository postRepository;

    public void saveTools(Post post, List<String> toolsName){

        if(toolsName.isEmpty()){
            return;
        }


        for(int i=0; i<toolsName.size() ; i++){
            Tools tools = Tools.of(toolsName.get(i), post);
            toolsRepository.save(tools);
        }
}

}
