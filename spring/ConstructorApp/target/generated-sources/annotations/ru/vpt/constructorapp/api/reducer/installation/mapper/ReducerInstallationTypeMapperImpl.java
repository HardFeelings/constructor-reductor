package ru.vpt.constructorapp.api.reducer.installation.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-31T01:05:19+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ReducerInstallationTypeMapperImpl implements ReducerInstallationTypeMapper {

    private final ReducerTypeMapper reducerTypeMapper;

    @Autowired
    public ReducerInstallationTypeMapperImpl(ReducerTypeMapper reducerTypeMapper) {

        this.reducerTypeMapper = reducerTypeMapper;
    }

    @Override
    public ReducerInstallationTypeDto toDTO(ReducerInstallationTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerInstallationTypeDto.ReducerInstallationTypeDtoBuilder reducerInstallationTypeDto = ReducerInstallationTypeDto.builder();

        reducerInstallationTypeDto.idReducerInstallationType( entity.getIdReducerInstallationType() );
        reducerInstallationTypeDto.reducerInstallationTypeValue( entity.getReducerInstallationTypeValue() );
        reducerInstallationTypeDto.reducerType( reducerTypeMapper.toDTO( entity.getReducerType() ) );

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
        reducerInstallationTypeEntity.setReducerType( reducerTypeMapper.toEntity( dto.getReducerType() ) );

        return reducerInstallationTypeEntity;
    }
}
