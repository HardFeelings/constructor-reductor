package ru.vpt.constructorapp.api.reducer.type.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ReducerTypeMapperImpl implements ReducerTypeMapper {

    @Override
    public ReducerTypeDto toDTO(ReducerTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerTypeDto.ReducerTypeDtoBuilder reducerTypeDto = ReducerTypeDto.builder();

        reducerTypeDto.idReducerType( entity.getIdReducerType() );
        reducerTypeDto.reducerTypeName( entity.getReducerTypeName() );

        return reducerTypeDto.build();
    }

    @Override
    public ReducerTypeEntity toEntity(ReducerTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerTypeEntity reducerTypeEntity = new ReducerTypeEntity();

        reducerTypeEntity.setIdReducerType( dto.getIdReducerType() );
        reducerTypeEntity.setReducerTypeName( dto.getReducerTypeName() );

        return reducerTypeEntity;
    }
}
