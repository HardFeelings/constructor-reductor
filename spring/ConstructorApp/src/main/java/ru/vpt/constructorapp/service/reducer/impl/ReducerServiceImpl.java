package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.service.reducer.ReducerService;
import ru.vpt.constructorapp.store.entities.reducer.*;
import ru.vpt.constructorapp.store.repo.reducer.ReducerRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return dtos;
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
        ReducerTypeEntity reducerType = reducerTypeServiceImpl.findById(dto.getReducerType().getIdReducerType())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип редуктора с id: " + dto.getReducerType().getIdReducerType()));
        ReducerSizeEntity reducerSize = reducerSizeServiceImpl.findById(dto.getReducerSize().getIdReducerSize())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден размер редуктора с id: " + dto.getReducerSize().getIdReducerSize()));
        ReducerInputTypeEntity reducerInputType = reducerInputTypeServiceImpl.findById(dto.getReducerInputType().getIdReducerInputType())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип входа редуктора с id: " + dto.getReducerInputType().getIdReducerInputType()));
        ReducerAdapterTypeEntity reducerAdapterType = reducerAdapterTypeServiceImpl.findById(dto.getReducerAdapterType().getIdReducerAdapterType())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип переходника редуктора с id: " + dto.getReducerAdapterType().getIdReducerAdapterType()));
        ReducerOutputShaftTypeEntity reducerOutputShaftType = reducerOutputShaftTypeServiceImpl.findById(dto.getReducerOutputShaftType().getIdReducerOutputShaftType())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип выходного вала с id: " + dto.getReducerOutputShaftType().getIdReducerOutputShaftType()));
        ReducerInstallationTypeEntity reducerInstallationType = reducerInstallationTypeServiceImpl.findById(dto.getReducerInstallationType().getIdReducerInstallationType())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найден тип установки редуктора с id: " + dto.getReducerInstallationType().getIdReducerInstallationType()));
        ReducerMountingEntity reducerMounting = reducerMountingServiceImpl.findById(dto.getReducerMounting().getIdReducerMounting())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить редуктор: не найдено крепление редуктора с id: " + dto.getReducerMounting().getIdReducerMounting()));
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
