package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerTypeRepo;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReducerTypeServiceImpl implements ReducerTypeService {

    private final ReducerTypeRepo reducerTypeRepo;
    private final ReducerTypeMapper reducerTypeMapper;

    @Override
    public List<ReducerTypeDto> getAllReducerTypes() {
        List<ReducerTypeEntity> entities = reducerTypeRepo.findAll();
        List<ReducerTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerTypeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerTypeDto getReducerTypeById(Long id) {
        ReducerTypeEntity entity = reducerTypeRepo.findById(id).get();
        return reducerTypeMapper.toDTO(entity);
    }
}
