package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.reducer.input.mapper.ReducerInputTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerInputTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerInputTypeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReducerInputTypeServiceImpl implements ReducerInputTypeService {

    private final ReducerInputTypeMapper reducerInputTypeMapper;
    private final ReducerInputTypeRepo reducerInputTypeRepo;
    private final ReducerTypeServiceImpl reducerTypeService;

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

    @Override
    public ReducerInputTypeDto saveReducerInputType(ReducerInputTypeDto dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Невозможно сохранить тип входа редуктора: dto равен null");
        }
        ReducerTypeEntity reducerTypeEntity = reducerTypeService.findById(dto.getReducerTypeId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить тип входа редуктора: не найден тип редуктора с id: " + dto.getReducerTypeId()));
        ReducerInputTypeEntity entity = reducerInputTypeMapper.toEntity(dto);
        entity.setReducerType(reducerTypeEntity);
        return reducerInputTypeMapper.toDTO(reducerInputTypeRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerInputType(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить тип входа редуктора: id равен null");
        }
        if (!reducerInputTypeRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить тип входа редуктора: не найден объект с id: " + id);
        }
        reducerInputTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<ReducerInputTypeEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно получить тип входа редуктора: id равен null");
        }
        return reducerInputTypeRepo.findById(id);
    }
}
