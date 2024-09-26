package ru.vpt.constructorapp.api.reducer.output.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ReducerOutputShaftTypeMapperImpl implements ReducerOutputShaftTypeMapper {

    @Override
    public ReducerOutputShaftTypeDto toDTO(ReducerOutputShaftTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerOutputShaftTypeDto.ReducerOutputShaftTypeDtoBuilder reducerOutputShaftTypeDto = ReducerOutputShaftTypeDto.builder();

        reducerOutputShaftTypeDto.reducerTypeId( entityReducerTypeIdReducerType( entity ) );
        reducerOutputShaftTypeDto.idReducerOutputShaftType( entity.getIdReducerOutputShaftType() );
        reducerOutputShaftTypeDto.reducerOutputShaftTypeValue( entity.getReducerOutputShaftTypeValue() );

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

        return reducerOutputShaftTypeEntity;
    }

    private Long entityReducerTypeIdReducerType(ReducerOutputShaftTypeEntity reducerOutputShaftTypeEntity) {
        if ( reducerOutputShaftTypeEntity == null ) {
            return null;
        }
        ReducerTypeEntity reducerType = reducerOutputShaftTypeEntity.getReducerType();
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
