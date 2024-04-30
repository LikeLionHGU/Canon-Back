package org.example.canon.service;

import lombok.RequiredArgsConstructor;
import org.example.canon.repository.ToolsRepository;
import org.example.canon.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToolsService {

    private final ToolsRepository toolsRepository;
    private final UserRepository userRepository;


    //List랑 유저를 인자로 받아서 그 크기만큼 반복
        // List 하나에 있는 거 유저 정보랑 같이 save


}
