package ru.vpt.constructorapp.api.motor.type.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MotorTypeMapper {
    MotorTypeDto toDTO(MotorTypeEntity entity);
    MotorTypeEntity toEntity(MotorTypeDto dto);
}
