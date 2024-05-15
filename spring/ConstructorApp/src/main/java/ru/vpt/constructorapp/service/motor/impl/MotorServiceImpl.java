package ru.vpt.constructorapp.service.motor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.motor.common.mapper.MotorMapper;
import ru.vpt.constructorapp.service.motor.MotorService;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;
import ru.vpt.constructorapp.store.repo.motor.MotorRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {
    private final MotorRepo motorRepo;
    private final MotorMapper motorMapper;
    private final MotorTypeServiceImpl motorTypeService;
    private final MotorAdapterTypeServiceImpl motorAdapterTypeService;

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

  @Override
  public MotorDto saveMotor(MotorDto motorDto) {
    if (Objects.isNull(motorDto)) {
      throw new RuntimeException("Невозможно сохранить мотор: dto равен null");
    }
    MotorTypeEntity motorTypeEntity = motorTypeService.findById(motorDto.getMotorTypeId());
    MotorAdapterTypeEntity motorAdapterTypeEntity = motorAdapterTypeService.findById(motorDto.getMotorAdapterTypeId());
    if (Objects.isNull(motorTypeEntity) || Objects.isNull(motorAdapterTypeEntity)) {
      throw new RuntimeException("Невозможно сохранить мотор: не найден тип мотора или адаптер типа мотора");
    }
    MotorEntity entity = motorMapper.toEntity(motorDto);
    entity.setMotorType(motorTypeEntity);
    entity.setMotorAdapterType(motorAdapterTypeEntity);
    return motorMapper.toDTO(motorRepo.save(entity));
  }

  @Override
  public Boolean deleteMotor(Long id) {
    if (Objects.isNull(id)) {
      throw new RuntimeException("Невозможно удалить объект: id равен null");
    }
    if (!motorRepo.existsById(id)) {
      throw new RuntimeException("Невозможно удалить объект: не найден объект с id: " + id);
    }
    motorRepo.deleteById(id);
    return true;
  }
}
