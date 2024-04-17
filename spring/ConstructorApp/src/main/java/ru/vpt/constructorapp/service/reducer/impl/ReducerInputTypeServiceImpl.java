package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.reducer.input.mapper.ReducerInputTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerInputTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerInputTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReducerInputTypeServiceImpl implements ReducerInputTypeService {

    private final ReducerInputTypeMapper reducerInputTypeMapper;
    private final ReducerInputTypeRepo reducerInputTypeRepo;

    @Override
    public List<ReducerInputTypeDto> getAllReducerInputTypes() {
        List<ReducerInputTypeEntity> entities = reducerInputTypeRepo.findAll();
        List<ReducerInputTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerInputTypeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerInputTypeDto getReducerInputById(Long id) {
        ReducerInputTypeEntity entity = reducerInputTypeRepo.findById(id).get();
        return reducerInputTypeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerInputTypeDto> getAllReducerInputTypesByReducerTypeId(Long id) {
        List<ReducerInputTypeEntity> entities = reducerInputTypeRepo.findReducerInputTypeEntitiesByReducerType_IdReducerType(id);
        List<ReducerInputTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerInputTypeMapper.toDTO(item)));
        return dtos;
    }
}
