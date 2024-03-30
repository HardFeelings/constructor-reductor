package ru.vpt.constructorapp.api.reducer.output.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-31T01:05:19+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ReducerOutputShaftTypeMapperImpl implements ReducerOutputShaftTypeMapper {

    private final ReducerTypeMapper reducerTypeMapper;

    @Autowired
    public ReducerOutputShaftTypeMapperImpl(ReducerTypeMapper reducerTypeMapper) {

        this.reducerTypeMapper = reducerTypeMapper;
    }

    @Override
    public ReducerOutputShaftTypeDto toDTO(ReducerOutputShaftTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerOutputShaftTypeDto.ReducerOutputShaftTypeDtoBuilder reducerOutputShaftTypeDto = ReducerOutputShaftTypeDto.builder();

        reducerOutputShaftTypeDto.idReducerOutputShaftType( entity.getIdReducerOutputShaftType() );
        reducerOutputShaftTypeDto.reducerOutputShaftTypeValue( entity.getReducerOutputShaftTypeValue() );
        reducerOutputShaftTypeDto.reducerType( reducerTypeMapper.toDTO( entity.getReducerType() ) );

        return reducerOutputShaftTypeDto.build();
    }

    @Override
    public ReducerOutputShaftTypeEntity toEntity(ReducerOutputShaftTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerOutputShaftTypeEntity reducerOutputShaftTypeEntity = new ReducerOutputShaftTypeEntity();

        reducerOutputShaftTypeEntity.setIdReducerOutputShaftType( dto.getIdReducerOutputShaftType() );
        reducerOutputShaftTypeEntity.setReducerOutputShaftTypeValue( dto.getReducerOutputShaftTypeValue() );
        reducerOutputShaftTypeEntity.setReducerType( reducerTypeMapper.toEntity( dto.getReducerType() ) );

        return reducerOutputShaftTypeEntity;
    }
}
