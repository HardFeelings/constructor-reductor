package ru.vpt.constructorapp.service.motor;

import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;

import java.util.List;

public interface MotorTypeService {
    List<MotorTypeDto> getAllMotorTypes();
    MotorTypeDto getMotorTypeById(Long id);
}
