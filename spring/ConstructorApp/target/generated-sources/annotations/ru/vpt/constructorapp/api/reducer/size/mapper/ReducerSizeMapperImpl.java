package ru.vpt.constructorapp.api.reducer.size.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-31T01:05:19+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ReducerSizeMapperImpl implements ReducerSizeMapper {

    private final ReducerTypeMapper reducerTypeMapper;

    @Autowired
    public ReducerSizeMapperImpl(ReducerTypeMapper reducerTypeMapper) {

        this.reducerTypeMapper = reducerTypeMapper;
    }

    @Override
    public ReducerSizeDto toDTO(ReducerSizeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerSizeDto.ReducerSizeDtoBuilder reducerSizeDto = ReducerSizeDto.builder();

        reducerSizeDto.idReducerSize( entity.getIdReducerSize() );
        reducerSizeDto.reducerSizeValue( entity.getReducerSizeValue() );
        reducerSizeDto.reducerType( reducerTypeMapper.toDTO( entity.getReducerType() ) );

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
        reducerSizeEntity.setReducerType( reducerTypeMapper.toEntity( dto.getReducerType() ) );

        return reducerSizeEntity;
    }
}
