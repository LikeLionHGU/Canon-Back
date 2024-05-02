package org.example.canon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.entity.Tools;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolDTO {

    private List<String> tools;


    public static ToolDTO of(List<Tools> toolsList) {
        List<String> tools = toolsList.stream()
                .map(Tools::getTool)
                .collect(Collectors.toList());
        return ToolDTO.builder()
                .tools(tools)
                .build();
    }
}