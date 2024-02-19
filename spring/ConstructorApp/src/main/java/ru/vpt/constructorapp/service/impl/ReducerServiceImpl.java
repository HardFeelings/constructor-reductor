package ru.vpt.constructorapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.service.ReducerService;
import ru.vpt.constructorapp.store.entities.ReducerEntity;
import ru.vpt.constructorapp.store.repo.ReducerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReducerServiceImpl implements ReducerService {
    private final ReducerRepository reducerRepository;
    private final ReducerMapper reducerMapper;
    @Override
    public List<ReducerDto> getAllReducer() {
        List<ReducerDto> reducerDtos = new ArrayList<>();
        reducerRepository.findAll().forEach(item -> {
            reducerDtos.add(reducerMapper.toDTO(item));
        });
        return reducerDtos;
    }

    @Override
    public ReducerDto getById(Long id) {
        ReducerEntity entity = reducerRepository.findById(id).get();
        if(entity == null)
            return null;
        return reducerMapper.toDTO(entity);
    }
}
