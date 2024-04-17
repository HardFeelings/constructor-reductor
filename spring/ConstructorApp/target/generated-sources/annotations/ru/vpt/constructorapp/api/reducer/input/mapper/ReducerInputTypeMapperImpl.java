package ru.vpt.constructorapp.api.reducer.input.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-17T13:11:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ReducerInputTypeMapperImpl implements ReducerInputTypeMapper {

    private final ReducerTypeMapper reducerTypeMapper;

    @Autowired
    public ReducerInputTypeMapperImpl(ReducerTypeMapper reducerTypeMapper) {

        this.reducerTypeMapper = reducerTypeMapper;
    }

    @Override
    public ReducerInputTypeDto toDTO(ReducerInputTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerInputTypeDto.ReducerInputTypeDtoBuilder reducerInputTypeDto = ReducerInputTypeDto.builder();

        reducerInputTypeDto.idReducerInputType( entity.getIdReducerInputType() );
        reducerInputTypeDto.reducerInputTypeValue( entity.getReducerInputTypeValue() );
        reducerInputTypeDto.reducerType( reducerTypeMapper.toDTO( entity.getReducerType() ) );

        return reducerInputTypeDto.build();
    }

    @Override
    public ReducerInputTypeEntity toEntity(ReducerInputTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerInputTypeEntity reducerInputTypeEntity = new ReducerInputTypeEntity();

        reducerInputTypeEntity.setIdReducerInputType( dto.getIdReducerInputType() );
        reducerInputTypeEntity.setReducerInputTypeValue( dto.getReducerInputTypeValue() );
        reducerInputTypeEntity.setReducerType( reducerTypeMapper.toEntity( dto.getReducerType() ) );

        return reducerInputTypeEntity;
    }
}
