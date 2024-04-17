package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.reducer.adapter.mapper.ReducerAdapterTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerAdapterTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerAdapterTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerAdapterTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReducerAdapterTypeServiceImpl implements ReducerAdapterTypeService {

    private final ReducerAdapterTypeMapper reducerAdapterTypeMapper;
    private final ReducerAdapterTypeRepo reducerAdapterTypeRepo;

    @Override
    public List<ReducerAdapterTypeDto> getAllReducerAdapterTypes() {
        List<ReducerAdapterTypeEntity> entities = reducerAdapterTypeRepo.findAll();
        List<ReducerAdapterTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerAdapterTypeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerAdapterTypeDto getReducerAdapterById(Long id) {
        ReducerAdapterTypeEntity entity = reducerAdapterTypeRepo.findById(id).get();
        return reducerAdapterTypeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerAdapterTypeDto> getAllReducerAdapterTypesByReducerTypeId(Long id) {
        List<ReducerAdapterTypeEntity> entities = reducerAdapterTypeRepo.findReducerAdapterTypeEntitiesByReducerType_IdReducerType(id);
        List<ReducerAdapterTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerAdapterTypeMapper.toDTO(item)));
        return dtos;
    }
}
