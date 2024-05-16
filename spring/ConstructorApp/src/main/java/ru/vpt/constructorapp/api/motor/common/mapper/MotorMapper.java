package ru.vpt.constructorapp.api.motor.common.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.motor.type.mapper.MotorTypeMapper;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;

@Mapper(componentModel = "spring", uses = {MotorTypeMapper.class, MotorAdapterTypeDto.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MotorMapper {

    @Mapping(source = "entity.motorType.idMotorType", target = "motorTypeId")
    @Mapping(source = "entity.motorAdapterType.idMotorAdapterType", target = "motorAdapterTypeId")
    MotorDto toDTO(MotorEntity entity);

    @Mapping(target = "motorType", ignore = true)
    @Mapping(target = "motorAdapterType", ignore = true)
    MotorEntity toEntity(MotorDto dto);
}
