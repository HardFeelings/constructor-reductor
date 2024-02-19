package ru.vpt.constructorapp.api.input.node.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputNodeDto {
    private Long idInputNode;
    private String inputNodeName;
    private String imagePath;
}
