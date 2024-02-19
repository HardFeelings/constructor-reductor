package ru.vpt.constructorapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.input.node.dto.InputNodeDto;
import ru.vpt.constructorapp.api.input.node.mapper.InputNodeMapper;
import ru.vpt.constructorapp.service.InputNodeService;
import ru.vpt.constructorapp.store.entities.InputNodeEntity;
import ru.vpt.constructorapp.store.repo.InputNodeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InputNodeServiceImpl implements InputNodeService {
    private final InputNodeMapper inputNodeMapper;
    private final InputNodeRepository inputNodeRepository;
    @Override
    public List<InputNodeDto> getAllInputNode() {
        List<InputNodeDto> inputNodeDtos = new ArrayList<>();
        inputNodeRepository.findAll().forEach(item -> {
            inputNodeDtos.add(inputNodeMapper.toDTO(item));
        });
        return inputNodeDtos;
    }

    @Override
    public InputNodeDto getById(Long id) {
        InputNodeEntity entity = inputNodeRepository.findById(id).get();
        if(entity == null)
            return null;
        return inputNodeMapper.toDTO(entity);
    }
}
