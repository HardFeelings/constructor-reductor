package ru.vpt.constructorapp.service.motor;

import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;

import java.util.List;

public interface MotorAdapterTypeService {
    List<MotorAdapterTypeDto> getAllMotorAdapterTypes();
    MotorAdapterTypeDto getMotorAdapterTypeById(Long id);
    List<MotorAdapterTypeDto> getAllMotorAdapterTypesByMotorTypesId(Long id);
    MotorAdapterTypeDto saveMotorAdapterType(MotorAdapterTypeDto motorAdapterTypeDto);
    Boolean deleteMotorAdapterType(Long id);
    MotorAdapterTypeEntity findById(Long id);
}
