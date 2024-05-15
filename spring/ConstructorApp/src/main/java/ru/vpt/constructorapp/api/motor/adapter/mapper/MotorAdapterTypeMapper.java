package ru.vpt.constructorapp.api.motor.adapter.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.motor.type.mapper.MotorTypeMapper;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;

@Mapper(componentModel = "spring", uses = {MotorTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MotorAdapterTypeMapper {

    @Mapping(source = "entity.motorType.idMotorType", target = "motorTypeId")
    MotorAdapterTypeDto toDTO(MotorAdapterTypeEntity entity);

    @Mapping(target = "motorType", ignore = true)
    MotorAdapterTypeEntity toEntity(MotorAdapterTypeDto dto);
}
