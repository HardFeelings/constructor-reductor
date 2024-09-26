package ru.vpt.constructorapp.api.reducer.size.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ReducerSizeMapperImpl implements ReducerSizeMapper {

    @Override
    public ReducerSizeDto toDTO(ReducerSizeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerSizeDto.ReducerSizeDtoBuilder reducerSizeDto = ReducerSizeDto.builder();

        reducerSizeDto.reducerTypeId( entityReducerTypeIdReducerType( entity ) );
        reducerSizeDto.idReducerSize( entity.getIdReducerSize() );
        reducerSizeDto.reducerSizeValue( entity.getReducerSizeValue() );

        return reducerSizeDto.build();
    }

    @Override
    public ReducerSizeEntity toEntity(ReducerSizeDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerSizeEntity reducerSizeEntity = new ReducerSizeEntity();

        reducerSizeEntity.setIdReducerSize( dto.getIdReducerSize() );
        reducerSizeEntity.setReducerSizeValue( dto.getReducerSizeValue() );

        return reducerSizeEntity;
    }

    private Long entityReducerTypeIdReducerType(ReducerSizeEntity reducerSizeEntity) {
        if ( reducerSizeEntity == null ) {
            return null;
        }
        ReducerTypeEntity reducerType = reducerSizeEntity.getReducerType();
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
