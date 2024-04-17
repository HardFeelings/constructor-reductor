package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.reducer.output.mapper.ReducerOutputShaftTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerOutputShaftTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerOutputShaftTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReducerOutputShaftTypeServiceImpl implements ReducerOutputShaftTypeService {

    private final ReducerOutputShaftTypeMapper reducerOutputShaftTypeMapper;
    private final ReducerOutputShaftTypeRepo reducerOutputShaftTypeRepo;

    @Override
    public List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypes() {
        List<ReducerOutputShaftTypeEntity> entities = reducerOutputShaftTypeRepo.findAll();
        List<ReducerOutputShaftTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerOutputShaftTypeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerOutputShaftTypeDto getReducerOutputShaftById(Long id) {
        ReducerOutputShaftTypeEntity entity = reducerOutputShaftTypeRepo.findById(id).get();
        return reducerOutputShaftTypeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypesByReducerTypeId(Long id) {
        List<ReducerOutputShaftTypeEntity> entities = reducerOutputShaftTypeRepo.findReducerOutputShaftTypeEntitiesByReducerType_IdReducerType(id);
        List<ReducerOutputShaftTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerOutputShaftTypeMapper.toDTO(item)));
        return dtos;
    }
}
