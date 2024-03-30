package ru.vpt.constructorapp.api.reducer.mounting.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-31T01:05:19+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ReducerMountingMapperImpl implements ReducerMountingMapper {

    @Override
    public ReducerMountingDto toDTO(ReducerMountingEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerMountingDto.ReducerMountingDtoBuilder reducerMountingDto = ReducerMountingDto.builder();

        reducerMountingDto.idReducerMounting( entity.getIdReducerMounting() );
        reducerMountingDto.reducerMountingValue( entity.getReducerMountingValue() );

        return reducerMountingDto.build();
    }

    @Override
    public ReducerMountingEntity toEntity(ReducerMountingDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerMountingEntity reducerMountingEntity = new ReducerMountingEntity();

        reducerMountingEntity.setIdReducerMounting( dto.getIdReducerMounting() );
        reducerMountingEntity.setReducerMountingValue( dto.getReducerMountingValue() );

        return reducerMountingEntity;
    }
}
