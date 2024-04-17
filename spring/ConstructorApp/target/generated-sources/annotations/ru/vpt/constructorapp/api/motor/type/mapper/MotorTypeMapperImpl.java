package ru.vpt.constructorapp.api.motor.type.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-17T13:11:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class MotorTypeMapperImpl implements MotorTypeMapper {

    @Override
    public MotorTypeDto toDTO(MotorTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MotorTypeDto.MotorTypeDtoBuilder motorTypeDto = MotorTypeDto.builder();

        motorTypeDto.idMotorType( entity.getIdMotorType() );
        motorTypeDto.motorTypeName( entity.getMotorTypeName() );

        return motorTypeDto.build();
    }

    @Override
    public MotorTypeEntity toEntity(MotorTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        MotorTypeEntity motorTypeEntity = new MotorTypeEntity();

        motorTypeEntity.setIdMotorType( dto.getIdMotorType() );
        motorTypeEntity.setMotorTypeName( dto.getMotorTypeName() );

        return motorTypeEntity;
    }
}
