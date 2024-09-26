package ru.vpt.constructorapp.api.reducer.installation.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ReducerInstallationTypeMapperImpl implements ReducerInstallationTypeMapper {

    @Override
    public ReducerInstallationTypeDto toDTO(ReducerInstallationTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerInstallationTypeDto.ReducerInstallationTypeDtoBuilder reducerInstallationTypeDto = ReducerInstallationTypeDto.builder();

        reducerInstallationTypeDto.reducerTypeId( entityReducerTypeIdReducerType( entity ) );
        reducerInstallationTypeDto.idReducerInstallationType( entity.getIdReducerInstallationType() );
        reducerInstallationTypeDto.reducerInstallationTypeValue( entity.getReducerInstallationTypeValue() );

        return reducerInstallationTypeDto.build();
    }

    @Override
    public ReducerInstallationTypeEntity toEntity(ReducerInstallationTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerInstallationTypeEntity reducerInstallationTypeEntity = new ReducerInstallationTypeEntity();

        reducerInstallationTypeEntity.setIdReducerInstallationType( dto.getIdReducerInstallationType() );
        reducerInstallationTypeEntity.setReducerInstallationTypeValue( dto.getReducerInstallationTypeValue() );

        return reducerInstallationTypeEntity;
    }

    private Long entityReducerTypeIdReducerType(ReducerInstallationTypeEntity reducerInstallationTypeEntity) {
        if ( reducerInstallationTypeEntity == null ) {
            return null;
        }
        ReducerTypeEntity reducerType = reducerInstallationTypeEntity.getReducerType();
        if ( reducerType == null ) {
            return null;
        }
        Long idReducerType = reducerType.getIdReducerType();
        if ( idReducerType == null ) {
            return null;
        }
        return idReducerType;
    }
}
