package ru.vpt.constructorapp.service.motor;

import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;

import java.util.List;

public interface MotorAdapterTypeService {
    List<MotorAdapterTypeDto> getAllMotorAdapterTypes();
    MotorAdapterTypeDto getMotorAdapterTypeById(Long id);
    List<MotorAdapterTypeDto> getAllMotorAdapterTypesByMotorTypesId(Long id);
    MotorAdapterTypeDto saveMotorAdapterType(MotorAdapterTypeDto motorAdapterTypeDto);
    Boolean deleteMotorAdapterType(Long id);
}
