package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.reducer.installation.mapper.ReducerInstallationTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerInstallationTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerInstallationTypeRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReducerInstallationTypeServiceImpl implements ReducerInstallationTypeService {

    private final ReducerInstallationTypeMapper reducerInstallationTypeMapper;
    private final ReducerInstallationTypeRepo reducerInstallationTypeRepo;
    private final ReducerTypeServiceImpl reducerTypeService;

    @Override
    public List<ReducerInstallationTypeDto> getAllReducerInstallationTypes() {
        List<ReducerInstallationTypeEntity> entities = reducerInstallationTypeRepo.findAll();
        List<ReducerInstallationTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerInstallationTypeMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ReducerInstallationTypeDto::getIdReducerInstallationType)).collect(Collectors.toList());
    }

    @Override
    public ReducerInstallationTypeDto getReducerInstallationById(Long id) {
        ReducerInstallationTypeEntity entity = reducerInstallationTypeRepo.findById(id).get();
        return reducerInstallationTypeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerInstallationTypeDto> getAllReducerInstallationTypesByReducerTypeId(Long id) {
        List<ReducerInstallationTypeEntity> entities = reducerInstallationTypeRepo.findReducerInstallationTypeEntitiesByReducerType_IdReducerType(id);
        List<ReducerInstallationTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerInstallationTypeMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ReducerInstallationTypeDto::getIdReducerInstallationType)).collect(Collectors.toList());
    }

    @Override
    public ReducerInstallationTypeDto saveReducerInstallationType(ReducerInstallationTypeDto dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Невозможно сохранить тип установки редуктора: dto равен null");
        }
        ReducerTypeEntity reducerTypeEntity = reducerTypeService.findById(dto.getReducerTypeId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить тип установки редуктора: не найден тип редуктора с id: " + dto.getReducerTypeId()));
        ReducerInstallationTypeEntity entity = reducerInstallationTypeMapper.toEntity(dto);
        entity.setReducerType(reducerTypeEntity);
        return reducerInstallationTypeMapper.toDTO(reducerInstallationTypeRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerInstallationType(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить тип установки редуктора: id равен null");
        }
        if (!reducerInstallationTypeRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить тип установки редуктора: не найден объект с id: " + id);
        }
        reducerInstallationTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<ReducerInstallationTypeEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно получить тип установки редуктора: id равен null");
        }
        return reducerInstallationTypeRepo.findById(id);
    }
}
