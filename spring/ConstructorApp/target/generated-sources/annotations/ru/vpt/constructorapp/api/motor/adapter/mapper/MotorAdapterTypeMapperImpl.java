package ru.vpt.constructorapp.api.motor.adapter.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.motor.type.mapper.MotorTypeMapper;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-31T01:05:19+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class MotorAdapterTypeMapperImpl implements MotorAdapterTypeMapper {

    private final MotorTypeMapper motorTypeMapper;

    @Autowired
    public MotorAdapterTypeMapperImpl(MotorTypeMapper motorTypeMapper) {

        this.motorTypeMapper = motorTypeMapper;
    }

    @Override
    public MotorAdapterTypeDto toDTO(MotorAdapterTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MotorAdapterTypeDto.MotorAdapterTypeDtoBuilder motorAdapterTypeDto = MotorAdapterTypeDto.builder();

        motorAdapterTypeDto.idMotorAdapterType( entity.getIdMotorAdapterType() );
        motorAdapterTypeDto.motorAdapterTypeValue( entity.getMotorAdapterTypeValue() );
        motorAdapterTypeDto.motorType( motorTypeMapper.toDTO( entity.getMotorType() ) );

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
        motorAdapterTypeEntity.setMotorType( motorTypeMapper.toEntity( dto.getMotorType() ) );

        return motorAdapterTypeEntity;
    }
}
