package ru.vpt.constructorapp.api.reducer.common.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ReducerMapperImpl implements ReducerMapper {

    @Override
    public ReducerDto toDTO(ReducerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerDto.ReducerDtoBuilder reducerDto = ReducerDto.builder();

        reducerDto.reducerTypeId( entityReducerTypeIdReducerType( entity ) );
        reducerDto.reducerSizeId( entityReducerSizeIdReducerSize( entity ) );
        reducerDto.reducerInputTypeId( entityReducerInputTypeIdReducerInputType( entity ) );
        reducerDto.reducerOutputShaftTypeId( entityReducerOutputShaftTypeIdReducerOutputShaftType( entity ) );
        reducerDto.reducerInstallationTypeId( entityReducerInstallationTypeIdReducerInstallationType( entity ) );
        reducerDto.reducerMountingId( entityReducerMountingIdReducerMounting( entity ) );
        reducerDto.idReducer( entity.getIdReducer() );
        reducerDto.diameterOutputShaft( entity.getDiameterOutputShaft() );
        reducerDto.ratio( entity.getRatio() );

        return reducerDto.build();
    }

    @Override
    public ReducerEntity toEntity(ReducerDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerEntity reducerEntity = new ReducerEntity();

        reducerEntity.setIdReducer( dto.getIdReducer() );
        reducerEntity.setDiameterOutputShaft( dto.getDiameterOutputShaft() );
        reducerEntity.setRatio( dto.getRatio() );

        return reducerEntity;
    }

    private Long entityReducerTypeIdReducerType(ReducerEntity reducerEntity) {
        if ( reducerEntity == null ) {
            return null;
        }
        ReducerTypeEntity reducerType = reducerEntity.getReducerType();
        if ( reducerType == null ) {
            return null;
        }
        Long idReducerType = reducerType.getIdReducerType();
        if ( idReducerType == null ) {
            return null;
        }
        return idReducerType;
    }

    private Long entityReducerSizeIdReducerSize(ReducerEntity reducerEntity) {
        if ( reducerEntity == null ) {
            return null;
        }
        ReducerSizeEntity reducerSize = reducerEntity.getReducerSize();
        if ( reducerSize == null ) {
            return null;
        }
        Long idReducerSize = reducerSize.getIdReducerSize();
        if ( idReducerSize == null ) {
            return null;
        }
        return idReducerSize;
    }

    private Long entityReducerInputTypeIdReducerInputType(ReducerEntity reducerEntity) {
        if ( reducerEntity == null ) {
            return null;
        }
        ReducerInputTypeEntity reducerInputType = reducerEntity.getReducerInputType();
        if ( reducerInputType == null ) {
            return null;
        }
        Long idReducerInputType = reducerInputType.getIdReducerInputType();
        if ( idReducerInputType == null ) {
            return null;
        }
        return idReducerInputType;
    }

    private Long entityReducerOutputShaftTypeIdReducerOutputShaftType(ReducerEntity reducerEntity) {
        if ( reducerEntity == null ) {
            return null;
        }
        ReducerOutputShaftTypeEntity reducerOutputShaftType = reducerEntity.getReducerOutputShaftType();
        if ( reducerOutputShaftType == null ) {
            return null;
        }
        Long idReducerOutputShaftType = reducerOutputShaftType.getIdReducerOutputShaftType();
        if ( idReducerOutputShaftType == null ) {
            return null;
        }
        return idReducerOutputShaftType;
    }

    private Long entityReducerInstallationTypeIdReducerInstallationType(ReducerEntity reducerEntity) {
        if ( reducerEntity == null ) {
            return null;
        }
        ReducerInstallationTypeEntity reducerInstallationType = reducerEntity.getReducerInstallationType();
        if ( reducerInstallationType == null ) {
            return null;
        }
        Long idReducerInstallationType = reducerInstallationType.getIdReducerInstallationType();
        if ( idReducerInstallationType == null ) {
            return null;
        }
        return idReducerInstallationType;
    }

    private Long entityReducerMountingIdReducerMounting(ReducerEntity reducerEntity) {
        if ( reducerEntity == null ) {
            return null;
        }
        ReducerMountingEntity reducerMounting = reducerEntity.getReducerMounting();
        if ( reducerMounting == null ) {
            return null;
        }
        Long idReducerMounting = reducerMounting.getIdReducerMounting();
        if ( idReducerMounting == null ) {
            return null;
        }
        return idReducerMounting;
    }
}
