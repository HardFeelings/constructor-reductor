package ru.vpt.constructorapp.api.reducer.adapter.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerAdapterTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-17T13:11:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ReducerAdapterTypeMapperImpl implements ReducerAdapterTypeMapper {

    private final ReducerTypeMapper reducerTypeMapper;

    @Autowired
    public ReducerAdapterTypeMapperImpl(ReducerTypeMapper reducerTypeMapper) {

        this.reducerTypeMapper = reducerTypeMapper;
    }

    @Override
    public ReducerAdapterTypeDto toDTO(ReducerAdapterTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerAdapterTypeDto.ReducerAdapterTypeDtoBuilder reducerAdapterTypeDto = ReducerAdapterTypeDto.builder();

        reducerAdapterTypeDto.idReducerAdapterType( entity.getIdReducerAdapterType() );
        reducerAdapterTypeDto.reducerAdapterTypeValue( entity.getReducerAdapterTypeValue() );
        reducerAdapterTypeDto.reducerType( reducerTypeMapper.toDTO( entity.getReducerType() ) );

        return reducerAdapterTypeDto.build();
    }

    @Override
    public ReducerAdapterTypeEntity toEntity(ReducerAdapterTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerAdapterTypeEntity reducerAdapterTypeEntity = new ReducerAdapterTypeEntity();

        reducerAdapterTypeEntity.setIdReducerAdapterType( dto.getIdReducerAdapterType() );
        reducerAdapterTypeEntity.setReducerAdapterTypeValue( dto.getReducerAdapterTypeValue() );
        reducerAdapterTypeEntity.setReducerType( reducerTypeMapper.toEntity( dto.getReducerType() ) );

        return reducerAdapterTypeEntity;
    }
}
