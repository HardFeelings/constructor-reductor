package ru.vpt.constructorapp.api.motor.adapter.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class MotorAdapterTypeMapperImpl implements MotorAdapterTypeMapper {

    @Override
    public MotorAdapterTypeDto toDTO(MotorAdapterTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MotorAdapterTypeDto.MotorAdapterTypeDtoBuilder motorAdapterTypeDto = MotorAdapterTypeDto.builder();

        motorAdapterTypeDto.motorTypeId( entityMotorTypeIdMotorType( entity ) );
        motorAdapterTypeDto.idMotorAdapterType( entity.getIdMotorAdapterType() );
        motorAdapterTypeDto.motorAdapterTypeValue( entity.getMotorAdapterTypeValue() );

        return motorAdapterTypeDto.build();
    }

    @Override
    public MotorAdapterTypeEntity toEntity(MotorAdapterTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        MotorAdapterTypeEntity motorAdapterTypeEntity = new MotorAdapterTypeEntity();

        motorAdapterTypeEntity.setIdMotorAdapterType( dto.getIdMotorAdapterType() );
        motorAdapterTypeEntity.setMotorAdapterTypeValue( dto.getMotorAdapterTypeValue() );

        return motorAdapterTypeEntity;
    }

    private Long entityMotorTypeIdMotorType(MotorAdapterTypeEntity motorAdapterTypeEntity) {
        if ( motorAdapterTypeEntity == null ) {
            return null;
        }
        MotorTypeEntity motorType = motorAdapterTypeEntity.getMotorType();
        if ( motorType == null ) {
            return null;
        }
        Long idMotorType = motorType.getIdMotorType();
        if ( idMotorType == null ) {
            return null;
        }
        return idMotorType;
    }
}
