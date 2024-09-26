package ru.vpt.constructorapp.api.motor.common.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class MotorMapperImpl implements MotorMapper {

    @Override
    public MotorDto toDTO(MotorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MotorDto.MotorDtoBuilder motorDto = MotorDto.builder();

        motorDto.motorTypeId( entityMotorTypeIdMotorType( entity ) );
        motorDto.motorAdapterTypeId( entityMotorAdapterTypeIdMotorAdapterType( entity ) );
        motorDto.idMotor( entity.getIdMotor() );
        motorDto.power( entity.getPower() );
        motorDto.frequency( entity.getFrequency() );
        motorDto.efficiency( entity.getEfficiency() );
        motorDto.ratedCurrent( entity.getRatedCurrent() );
        motorDto.momentOfInertia( entity.getMomentOfInertia() );

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
        motorEntity.setEfficiency( dto.getEfficiency() );
        motorEntity.setRatedCurrent( dto.getRatedCurrent() );
        motorEntity.setMomentOfInertia( dto.getMomentOfInertia() );

        return motorEntity;
    }

    private Long entityMotorTypeIdMotorType(MotorEntity motorEntity) {
        if ( motorEntity == null ) {
            return null;
        }
        MotorTypeEntity motorType = motorEntity.getMotorType();
        if ( motorType == null ) {
            return null;
        }
        Long idMotorType = motorType.getIdMotorType();
        if ( idMotorType == null ) {
            return null;
        }
        return idMotorType;
    }

    private Long entityMotorAdapterTypeIdMotorAdapterType(MotorEntity motorEntity) {
        if ( motorEntity == null ) {
            return null;
        }
        MotorAdapterTypeEntity motorAdapterType = motorEntity.getMotorAdapterType();
        if ( motorAdapterType == null ) {
            return null;
        }
        Long idMotorAdapterType = motorAdapterType.getIdMotorAdapterType();
        if ( idMotorAdapterType == null ) {
            return null;
        }
        return idMotorAdapterType;
    }
}
