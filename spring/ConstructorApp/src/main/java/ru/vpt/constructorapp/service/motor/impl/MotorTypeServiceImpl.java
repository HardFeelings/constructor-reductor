package ru.vpt.constructorapp.service.motor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.api.motor.type.mapper.MotorTypeMapper;
import ru.vpt.constructorapp.service.motor.MotorTypeService;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;
import ru.vpt.constructorapp.store.repo.motor.MotorTypeRepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return dtos.stream().sorted(Comparator.comparingLong(MotorTypeDto::getIdMotorType)).collect(Collectors.toList());

    }

    @Override
    public MotorTypeDto getMotorTypeById(Long id) {
        MotorTypeEntity entity = motorTypeRepo.findById(id).get();
        return motorTypeMapper.toDTO(entity);
    }

    @Override
    public MotorTypeDto saveMotorType(MotorTypeDto motorTypeDto) {
        if (Objects.isNull(motorTypeDto)) {
            throw new RuntimeException("Невозможно сохранить объект: dto равен null");
        }
        MotorTypeEntity entity = motorTypeMapper.toEntity(motorTypeDto);
        return motorTypeMapper.toDTO(motorTypeRepo.save(entity));
    }

    @Override
    public Boolean deleteMotorType(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить объект: id равен null");
        }
        if (!motorTypeRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить объект: не найден объект с id: " + id);
        }
        motorTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public MotorTypeEntity findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно получить тип мотора: id равен null");
        }
        return motorTypeRepo.findById(id).orElse(null);
    }
}
