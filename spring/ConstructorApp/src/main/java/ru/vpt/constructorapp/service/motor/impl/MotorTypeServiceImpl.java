package ru.vpt.constructorapp.service.motor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.api.motor.type.mapper.MotorTypeMapper;
import ru.vpt.constructorapp.service.motor.MotorTypeService;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;
import ru.vpt.constructorapp.store.repo.motor.MotorTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorTypeServiceImpl implements MotorTypeService {
    private final MotorTypeRepo motorTypeRepo;
    private final MotorTypeMapper motorTypeMapper;
    @Override
    public List<MotorTypeDto> getAllMotorTypes() {
        List<MotorTypeEntity> entities = motorTypeRepo.findAll();
        List<MotorTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(motorTypeMapper.toDTO(item)));
        return dtos;

    }

    @Override
    public MotorTypeDto getMotorTypeById(Long id) {
        MotorTypeEntity entity = motorTypeRepo.findById(id).get();
        return motorTypeMapper.toDTO(entity);
    }
}
