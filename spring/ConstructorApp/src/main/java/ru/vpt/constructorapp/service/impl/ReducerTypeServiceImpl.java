package ru.vpt.constructorapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.service.ReducerTypeService;
import ru.vpt.constructorapp.store.entities.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.ReducerTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReducerTypeServiceImpl implements ReducerTypeService {
    private final ReducerTypeRepository reducerTypeRepository;
    private final ReducerTypeMapper reducerTypeMapper;
    @Override
    public List<ReducerTypeDto> getAllReducerTypes() {
        List<ReducerTypeDto> reducerTypeDtos = new ArrayList<>();
        reducerTypeRepository.findAll().forEach(item -> {
            reducerTypeDtos.add(reducerTypeMapper.toDTO(item));
        });
        return reducerTypeDtos;
    }

    @Override
    public ReducerTypeDto getById(Long id) {
        ReducerTypeEntity entity = reducerTypeRepository.findById(id).get();
        if(entity == null)
            return null;
        return reducerTypeMapper.toDTO(entity);
    }
}
