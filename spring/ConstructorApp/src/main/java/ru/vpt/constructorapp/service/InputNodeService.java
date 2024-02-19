package ru.vpt.constructorapp.service;

import ru.vpt.constructorapp.api.input.node.dto.InputNodeDto;

import java.util.List;

public interface InputNodeService {
    List<InputNodeDto> getAllInputNode();
    InputNodeDto getById(Long id);
}
