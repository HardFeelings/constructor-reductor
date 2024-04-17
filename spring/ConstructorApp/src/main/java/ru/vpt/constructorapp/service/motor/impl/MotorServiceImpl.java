package ru.vpt.constructorapp.service.motor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.motor.common.mapper.MotorMapper;
import ru.vpt.constructorapp.service.motor.MotorService;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.repo.motor.MotorRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {
    private final MotorRepo motorRepo;
    private final MotorMapper motorMapper;
    @Override
    public List<MotorDto> getAllMotors() {
        List<MotorEntity> entities = motorRepo.findAll();
        List<MotorDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(motorMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public MotorDto getMotorById(Long id) {
        MotorEntity entity = motorRepo.findById(id).get();
        return motorMapper.toDTO(entity);
    }
}
