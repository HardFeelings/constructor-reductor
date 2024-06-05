package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.service.reducer.ReducerService;
import ru.vpt.constructorapp.store.entities.reducer.*;
import ru.vpt.constructorapp.store.repo.reducer.ReducerRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReducerServiceImpl implements ReducerService {

    private final ReducerMapper reducerMapper;
    private final ReducerRepo reducerRepo;
    private final ReducerTypeServiceImpl reducerTypeServiceImpl;
    private final ReducerSizeServiceImpl reducerSizeServiceImpl;
    private final ReducerInputTypeServiceImpl reducerInputTypeServiceImpl;
    private final ReducerAdapterTypeServiceImpl reducerAdapterTypeServiceImpl;
    private final ReducerOutputShaftTypeServiceImpl reducerOutputShaftTypeServiceImpl;
    private final ReducerInstallationTypeServiceImpl reducerInstallationTypeServiceImpl;
    private final ReducerMountingServiceImpl reducerMountingServiceImpl;

    @Override
    public List<ReducerDto> getAllReducer() {
        List<ReducerEntity> entities = reducerRepo.findAll();
        List<ReducerDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ReducerDto::getIdReducer)).collect(Collectors.toList());
    }

    @Override
    public ReducerDto getReducerById(Long id) {
        ReducerEntity entity = reducerRepo.findById(id).get();
        return reducerMapper.toDTO(entity);
    }

    @Override
    public ReducerDto saveReducer(ReducerDto dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Невозможно сохранить редуктор: dto равен null");
        }
        ReducerTypeEntity reducerType = reducerTypeServiceImpl.findById(dto.getReducerTypeId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип редуктора с id: " + dto.getReducerTypeId()));
        ReducerSizeEntity reducerSize = reducerSizeServiceImpl.findById(dto.getReducerSizeId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден размер редуктора с id: " + dto.getReducerSizeId()));
        ReducerInputTypeEntity reducerInputType = reducerInputTypeServiceImpl.findById(dto.getReducerInputTypeId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип входа редуктора с id: " + dto.getReducerInputTypeId()));
        ReducerAdapterTypeEntity reducerAdapterType = reducerAdapterTypeServiceImpl.findById(dto.getReducerAdapterTypeId()).get();
        ReducerOutputShaftTypeEntity reducerOutputShaftType = reducerOutputShaftTypeServiceImpl.findById(dto.getReducerOutputShaftTypeId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип выходного вала с id: " + dto.getReducerOutputShaftTypeId()));
        ReducerInstallationTypeEntity reducerInstallationType = reducerInstallationTypeServiceImpl.findById(dto.getReducerInstallationTypeId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип установки редуктора с id: " + dto.getReducerInstallationTypeId()));
        ReducerMountingEntity reducerMounting = reducerMountingServiceImpl.findById(dto.getReducerMountingId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найдено крепление редуктора с id: " + dto.getReducerMountingId()));
        ReducerEntity entity = reducerMapper.toEntity(dto);
        entity.setReducerType(reducerType);
        entity.setReducerSize(reducerSize);
        entity.setReducerInputType(reducerInputType);
        entity.setReducerAdapterType(reducerAdapterType);
        entity.setReducerOutputShaftType(reducerOutputShaftType);
        entity.setReducerInstallationType(reducerInstallationType);
        entity.setReducerMounting(reducerMounting);
        return reducerMapper.toDTO(reducerRepo.save(entity));
    }

    @Override
    public Boolean deleteReducer(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить редуктор: id равен null");
        }
        if (!reducerRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить редуктор: не найден объект с id: " + id);
        }
        reducerRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<ReducerEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно получить редуктор: id равен null");
        }
        return reducerRepo.findById(id);
    }
}
