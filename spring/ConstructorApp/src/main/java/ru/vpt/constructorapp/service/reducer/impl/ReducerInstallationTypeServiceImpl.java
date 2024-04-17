package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.reducer.installation.mapper.ReducerInstallationTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerInstallationTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerInstallationTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReducerInstallationTypeServiceImpl implements ReducerInstallationTypeService {

    private final ReducerInstallationTypeMapper reducerInstallationTypeMapper;
    private final ReducerInstallationTypeRepo reducerInstallationTypeRepo;

    @Override
    public List<ReducerInstallationTypeDto> getAllReducerInstallationTypes() {
        List<ReducerInstallationTypeEntity> entities = reducerInstallationTypeRepo.findAll();
        List<ReducerInstallationTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerInstallationTypeMapper.toDTO(item)));
        return dtos;
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
        return dtos;
    }
}
