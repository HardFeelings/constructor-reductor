package ru.vpt.constructorapp.service.motor;

import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;

import java.util.List;

public interface MotorService {
    List<MotorDto> getAllMotors();
    MotorDto getMotorById(Long id);
    MotorDto saveMotor(MotorDto motorDto);
    Boolean deleteMotor(Long id);
}
