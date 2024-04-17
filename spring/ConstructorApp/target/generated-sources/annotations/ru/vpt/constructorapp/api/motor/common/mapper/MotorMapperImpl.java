package ru.vpt.constructorapp.api.motor.common.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.motor.type.mapper.MotorTypeMapper;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-17T13:11:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class MotorMapperImpl implements MotorMapper {

    private final MotorTypeMapper motorTypeMapper;

    @Autowired
    public MotorMapperImpl(MotorTypeMapper motorTypeMapper) {

        this.motorTypeMapper = motorTypeMapper;
    }

    @Override
    public MotorDto toDTO(MotorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MotorDto.MotorDtoBuilder motorDto = MotorDto.builder();

        motorDto.idMotor( entity.getIdMotor() );
        motorDto.power( entity.getPower() );
        motorDto.frequency( entity.getFrequency() );
        motorDto.rpm( entity.getRpm() );
        motorDto.motorType( motorTypeMapper.toDTO( entity.getMotorType() ) );
        motorDto.motorAdapterType( motorAdapterTypeEntityToMotorAdapterTypeDto( entity.getMotorAdapterType() ) );

        return motorDto.build();
    }

    @Override
    public MotorEntity toEntity(MotorDto dto) {
        if ( dto == null ) {
            return null;
        }

        MotorEntity motorEntity = new MotorEntity();

        motorEntity.setIdMotor( dto.getIdMotor() );
        motorEntity.setPower( dto.getPower() );
        motorEntity.setFrequency( dto.getFrequency() );
        motorEntity.setRpm( dto.getRpm() );
        motorEntity.setMotorType( motorTypeMapper.toEntity( dto.getMotorType() ) );
        motorEntity.setMotorAdapterType( motorAdapterTypeDtoToMotorAdapterTypeEntity( dto.getMotorAdapterType() ) );

        return motorEntity;
    }

    protected MotorAdapterTypeDto motorAdapterTypeEntityToMotorAdapterTypeDto(MotorAdapterTypeEntity motorAdapterTypeEntity) {
        if ( motorAdapterTypeEntity == null ) {
            return null;
        }

        MotorAdapterTypeDto.MotorAdapterTypeDtoBuilder motorAdapterTypeDto = MotorAdapterTypeDto.builder();

        motorAdapterTypeDto.idMotorAdapterType( motorAdapterTypeEntity.getIdMotorAdapterType() );
        motorAdapterTypeDto.motorAdapterTypeValue( motorAdapterTypeEntity.getMotorAdapterTypeValue() );
        motorAdapterTypeDto.motorType( motorTypeMapper.toDTO( motorAdapterTypeEntity.getMotorType() ) );

        return motorAdapterTypeDto.build();
    }

    protected MotorAdapterTypeEntity motorAdapterTypeDtoToMotorAdapterTypeEntity(MotorAdapterTypeDto motorAdapterTypeDto) {
        if ( motorAdapterTypeDto == null ) {
            return null;
        }

        MotorAdapterTypeEntity motorAdapterTypeEntity = new MotorAdapterTypeEntity();

        motorAdapterTypeEntity.setIdMotorAdapterType( motorAdapterTypeDto.getIdMotorAdapterType() );
        motorAdapterTypeEntity.setMotorAdapterTypeValue( motorAdapterTypeDto.getMotorAdapterTypeValue() );
        motorAdapterTypeEntity.setMotorType( motorTypeMapper.toEntity( motorAdapterTypeDto.getMotorType() ) );

        return motorAdapterTypeEntity;
    }
}
