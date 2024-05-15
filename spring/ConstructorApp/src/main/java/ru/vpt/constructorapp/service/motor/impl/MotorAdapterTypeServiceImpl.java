package ru.vpt.constructorapp.service.motor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.motor.adapter.mapper.MotorAdapterTypeMapper;
import ru.vpt.constructorapp.service.motor.MotorAdapterTypeService;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;
import ru.vpt.constructorapp.store.repo.motor.MotorAdapterTypeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MotorAdapterTypeServiceImpl implements MotorAdapterTypeService {
    private final MotorAdapterTypeRepo motorAdapterTypeRepo;
    private final MotorAdapterTypeMapper motorAdapterTypeMapper;
    private final MotorTypeServiceImpl motorTypeService;

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

    @Override
    public MotorAdapterTypeDto saveMotorAdapterType(MotorAdapterTypeDto motorAdapterTypeDto) {
        if (Objects.isNull(motorAdapterTypeDto)) {
            throw new RuntimeException("Невозможно сохранить объект: dto равен null");
        }
        MotorTypeEntity motorTypeEntity = motorTypeService.findById(motorAdapterTypeDto.getMotorTypeId());
        if (Objects.isNull(motorTypeEntity)) {
            throw new RuntimeException("Невозможно сохранить объект: не найден тип мотора с id: " + motorAdapterTypeDto.getMotorTypeId());
        }
        MotorAdapterTypeEntity entity = motorAdapterTypeMapper.toEntity(motorAdapterTypeDto);
        entity.setMotorType(motorTypeEntity);
        return motorAdapterTypeMapper.toDTO(motorAdapterTypeRepo.save(entity));
    }

    @Override
    public Boolean deleteMotorAdapterType(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить объект: id равен null");
        }
        if (!motorAdapterTypeRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить объект: не найден объект с id: " + id);
        }
        motorAdapterTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public MotorAdapterTypeEntity findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно получить адаптер типа мотора: id равен null");
        }
        return motorAdapterTypeRepo.findById(id).orElse(null);
    }
}
