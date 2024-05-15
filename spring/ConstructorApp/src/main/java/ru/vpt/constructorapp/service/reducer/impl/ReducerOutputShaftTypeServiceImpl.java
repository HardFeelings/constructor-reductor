package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.reducer.output.mapper.ReducerOutputShaftTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerOutputShaftTypeService;
import ru.vpt.constructorapp.service.reducer.ReducerTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerOutputShaftTypeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReducerOutputShaftTypeServiceImpl implements ReducerOutputShaftTypeService {

    private final ReducerOutputShaftTypeMapper reducerOutputShaftTypeMapper;
    private final ReducerOutputShaftTypeRepo reducerOutputShaftTypeRepo;
    private final ReducerTypeServiceImpl reducerTypeService;

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

    @Override
    public ReducerOutputShaftTypeDto saveReducerOutputShaftTypes(ReducerOutputShaftTypeDto dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Невозможно сохранить тип выходного вала: dto равен null");
        }
        ReducerTypeEntity reducerTypeEntity = reducerTypeService.findById(dto.getReducerTypeId());
        if (Objects.isNull(reducerTypeEntity)) {
            throw new RuntimeException("Невозможно сохранить тип выходного вала: не найден тип редуктора с id: " + dto.getReducerTypeId());
        }
        ReducerOutputShaftTypeEntity entity = reducerOutputShaftTypeMapper.toEntity(dto);
        entity.setReducerType(reducerTypeEntity);
        return reducerOutputShaftTypeMapper.toDTO(reducerOutputShaftTypeRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerOutputShaftTypes(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить тип выходного вала: id равен null");
        }
        if (!reducerOutputShaftTypeRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить тип выходного вала: не найден объект с id: " + id);
        }
        reducerOutputShaftTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public ReducerOutputShaftTypeEntity findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно получить тип выходного вала: id равен null");
        }
        return reducerOutputShaftTypeRepo.findById(id).orElse(null);
    }
}
