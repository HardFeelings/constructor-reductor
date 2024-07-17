package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.reducer.adapter.mapper.ReducerAdapterTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerAdapterTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerAdapterTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerAdapterTypeRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReducerAdapterTypeServiceImpl implements ReducerAdapterTypeService {

    private final ReducerAdapterTypeMapper reducerAdapterTypeMapper;
    private final ReducerAdapterTypeRepo reducerAdapterTypeRepo;
    private final ReducerTypeServiceImpl reducerTypeService;

    @Override
    public List<ReducerAdapterTypeDto> getAllReducerAdapterTypes() {
        List<ReducerAdapterTypeEntity> entities = reducerAdapterTypeRepo.findAll();
        List<ReducerAdapterTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerAdapterTypeMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ReducerAdapterTypeDto::getIdReducerAdapterType)).collect(Collectors.toList());
    }

    @Override
    public ReducerAdapterTypeDto getReducerAdapterById(Long id) {
        ReducerAdapterTypeEntity entity = reducerAdapterTypeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("reducerAdapterType with id = " + id + " not found", 404));
        return reducerAdapterTypeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerAdapterTypeDto> getAllReducerAdapterTypesByReducerTypeId(Long id) {
        List<ReducerAdapterTypeEntity> entities = reducerAdapterTypeRepo.findReducerAdapterTypeEntitiesByReducerType_IdReducerType(id);
        List<ReducerAdapterTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerAdapterTypeMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ReducerAdapterTypeDto::getIdReducerAdapterType)).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteReducerAdapterType(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить тип переходника редуктора: id равен null", 400);
        }
        if (!reducerAdapterTypeRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить тип переходника редуктора: не найден объект с id: " + id, 404);
        }
        reducerAdapterTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public ReducerAdapterTypeDto saveReducerAdapterType(ReducerAdapterTypeDto dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException("Невозможно сохранить тип переходника редуктора: dto равен null", 400);
        }
        ReducerTypeEntity reducerTypeEntity = reducerTypeService.findById(dto.getReducerTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить тип переходника редуктора: не найден тип редуктора с id: " + dto.getReducerTypeId(), 404));
        ReducerAdapterTypeEntity entity = reducerAdapterTypeMapper.toEntity(dto);
        entity.setReducerType(reducerTypeEntity);
        return reducerAdapterTypeMapper.toDTO(reducerAdapterTypeRepo.save(entity));
    }

    @Override
    public Optional<ReducerAdapterTypeEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно получить тип переходника редуктора: id равен null", 400);
        }
        return reducerAdapterTypeRepo.findById(id);
    }
}
