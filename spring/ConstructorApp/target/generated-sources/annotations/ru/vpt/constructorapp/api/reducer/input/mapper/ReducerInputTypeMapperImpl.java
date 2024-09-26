package ru.vpt.constructorapp.api.reducer.input.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ReducerInputTypeMapperImpl implements ReducerInputTypeMapper {

    @Override
    public ReducerInputTypeDto toDTO(ReducerInputTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerInputTypeDto.ReducerInputTypeDtoBuilder reducerInputTypeDto = ReducerInputTypeDto.builder();

        reducerInputTypeDto.reducerTypeId( entityReducerTypeIdReducerType( entity ) );
        reducerInputTypeDto.idReducerInputType( entity.getIdReducerInputType() );
        reducerInputTypeDto.reducerInputTypeValue( entity.getReducerInputTypeValue() );

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

        return reducerInputTypeEntity;
    }

    private Long entityReducerTypeIdReducerType(ReducerInputTypeEntity reducerInputTypeEntity) {
        if ( reducerInputTypeEntity == null ) {
            return null;
        }
        ReducerTypeEntity reducerType = reducerInputTypeEntity.getReducerType();
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
