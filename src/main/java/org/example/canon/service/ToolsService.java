package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.dto.PostDTO;
import org.example.canon.dto.ToolDTO;
import org.example.canon.entity.Post;
import org.example.canon.entity.Tools;
import org.example.canon.repository.PostRepository;
import org.example.canon.repository.ToolsRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

  public List<ToolDTO> getAllByPostId(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow();
    List<Tools> tools = toolsRepository.findAllByPost(post);
      return tools.stream().map(tool -> ToolDTO.of(tools)).collect(Collectors.toList());
    }

}
