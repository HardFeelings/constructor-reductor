package ru.vpt.constructorapp.service.motor;

import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;

import java.util.List;

public interface MotorTypeService {
    List<MotorTypeDto> getAllMotorTypes();
    MotorTypeDto getMotorTypeById(Long id);
    MotorTypeEntity findById(Long id);
    MotorTypeDto saveMotorType(MotorTypeDto motorTypeDto);
    Boolean deleteMotorType(Long id);
}
