package ru.vpt.constructorapp.service.motor;

import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;

import java.util.List;
import java.util.Optional;

public interface MotorService {
    List<MotorDto> getAllMotors();
    MotorDto getMotorById(Long id);
    MotorDto saveMotor(MotorDto motorDto);

    MotorEntity saveMotorEntity(MotorDto motorDto);

    Boolean deleteMotor(Long id);
    List<MotorEntity> findByFilter(MotorDto motorDto);
    Optional<MotorEntity> findById(Long id);
}
