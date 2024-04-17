package ru.vpt.constructorapp.service.motor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.motor.adapter.mapper.MotorAdapterTypeMapper;
import ru.vpt.constructorapp.service.motor.MotorAdapterTypeService;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;
import ru.vpt.constructorapp.store.repo.motor.MotorAdapterTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorAdapterTypeServiceImpl implements MotorAdapterTypeService {
    private final MotorAdapterTypeRepo motorAdapterTypeRepo;
    private final MotorAdapterTypeMapper motorAdapterTypeMapper;
    @Override
    public List<MotorAdapterTypeDto> getAllMotorAdapterTypes() {
        List<MotorAdapterTypeEntity> entities = motorAdapterTypeRepo.findAll();
        List<MotorAdapterTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(motorAdapterTypeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public MotorAdapterTypeDto getMotorAdapterTypeById(Long id) {
        MotorAdapterTypeEntity entity = motorAdapterTypeRepo.findById(id).get();
        return motorAdapterTypeMapper.toDTO(entity);
    }

    @Override
    public List<MotorAdapterTypeDto> getAllMotorAdapterTypesByMotorTypesId(Long id) {
        List<MotorAdapterTypeEntity> entities = motorAdapterTypeRepo.findMotorAdapterTypeEntitiesByMotorType_IdMotorType(id);
        List<MotorAdapterTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(motorAdapterTypeMapper.toDTO(item)));
        return dtos;
    }
}
