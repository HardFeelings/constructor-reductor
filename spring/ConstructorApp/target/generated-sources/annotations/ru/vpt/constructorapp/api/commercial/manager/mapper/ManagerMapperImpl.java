package ru.vpt.constructorapp.api.commercial.manager.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ManagerMapperImpl implements ManagerMapper {

    @Override
    public ManagerDto toDTO(ManagerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ManagerDto.ManagerDtoBuilder managerDto = ManagerDto.builder();

        managerDto.idManager( entity.getIdManager() );
        managerDto.shortName( entity.getShortName() );
        managerDto.fullName( entity.getFullName() );
        managerDto.position( entity.getPosition() );
        managerDto.email( entity.getEmail() );
        managerDto.phoneNumber( entity.getPhoneNumber() );

        return managerDto.build();
    }

    @Override
    public ManagerEntity toEntity(ManagerDto dto) {
        if ( dto == null ) {
            return null;
        }

        ManagerEntity managerEntity = new ManagerEntity();

        managerEntity.setIdManager( dto.getIdManager() );
        managerEntity.setShortName( dto.getShortName() );
        managerEntity.setFullName( dto.getFullName() );
        managerEntity.setPosition( dto.getPosition() );
        managerEntity.setEmail( dto.getEmail() );
        managerEntity.setPhoneNumber( dto.getPhoneNumber() );

        return managerEntity;
    }
}
