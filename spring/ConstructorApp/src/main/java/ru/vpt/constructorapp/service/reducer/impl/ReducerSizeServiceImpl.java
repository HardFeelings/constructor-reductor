package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.size.mapper.ReducerSizeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerSizeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerSizeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReducerSizeServiceImpl implements ReducerSizeService {

    private final ReducerSizeMapper reducerSizeMapper;
    private final ReducerSizeRepo reducerSizeRepo;

    @Override
    public List<ReducerSizeDto> getAllReducerSizes() {
        List<ReducerSizeEntity> entities = reducerSizeRepo.findAll();
        List<ReducerSizeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerSizeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerSizeDto getReducerSizeById(Long id) {
        ReducerSizeEntity entity = reducerSizeRepo.findById(id).get();
        return reducerSizeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerSizeDto> getAllReducerSizesByReducerTypeId(Long id) {
        List<ReducerSizeEntity> entities = reducerSizeRepo.findReducerSizeEntitiesByReducerType_IdReducerType(id);
        List<ReducerSizeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerSizeMapper.toDTO(item)));
        return dtos;
    }
}
