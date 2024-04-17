package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.service.reducer.ReducerService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReducerServiceImpl implements ReducerService {

    private final ReducerMapper reducerMapper;
    private final ReducerRepo reducerRepo;

    @Override
    public List<ReducerDto> getAllReducer() {
        List<ReducerEntity> entities = reducerRepo.findAll();
        List<ReducerDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerDto getReducerById(Long id) {
        ReducerEntity entity = reducerRepo.findById(id).get();
        return reducerMapper.toDTO(entity);
    }
}
