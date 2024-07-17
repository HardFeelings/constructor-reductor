package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerTypeRepo;

import java.util.*;
import java.util.stream.Collectors;

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
        return dtos.stream().sorted(Comparator.comparingLong(ReducerTypeDto::getIdReducerType)).collect(Collectors.toList());
    }

    @Override
    public ReducerTypeDto getReducerTypeById(Long id) {
        ReducerTypeEntity entity = reducerTypeRepo.findById(id).orElseThrow(() -> new NotFoundException("reducerType with id = " + id + " not found", 404));
        return reducerTypeMapper.toDTO(entity);
    }

    @Override
    public ReducerTypeDto saveReducerType(ReducerTypeDto dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException("Невозможно сохранить тип редуктора: dto равен null", 400);
        }
        ReducerTypeEntity entity = reducerTypeMapper.toEntity(dto);
        return reducerTypeMapper.toDTO(reducerTypeRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerType(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить тип редуктора: id равен null", 400);
        }
        if (!reducerTypeRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить тип редуктора: не найден объект с id: " + id, 404);
        }
        reducerTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<ReducerTypeEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно получить тип редуктора: id равен null", 400);
        }
        return reducerTypeRepo.findById(id);
    }
}
