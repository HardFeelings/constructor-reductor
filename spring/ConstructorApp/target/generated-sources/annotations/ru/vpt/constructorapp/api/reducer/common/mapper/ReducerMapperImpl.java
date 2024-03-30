package ru.vpt.constructorapp.api.reducer.common.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerAdapterTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-31T01:05:19+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ReducerMapperImpl implements ReducerMapper {

    private final ReducerTypeMapper reducerTypeMapper;

    @Autowired
    public ReducerMapperImpl(ReducerTypeMapper reducerTypeMapper) {

        this.reducerTypeMapper = reducerTypeMapper;
    }

    @Override
    public ReducerDto toDTO(ReducerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ReducerDto.ReducerDtoBuilder reducerDto = ReducerDto.builder();

        reducerDto.idReducer( entity.getIdReducer() );
        reducerDto.reducerType( reducerTypeMapper.toDTO( entity.getReducerType() ) );
        reducerDto.reducerSize( reducerSizeEntityToReducerSizeDto( entity.getReducerSize() ) );
        reducerDto.reducerInputType( reducerInputTypeEntityToReducerInputTypeDto( entity.getReducerInputType() ) );
        reducerDto.reducerAdapterType( reducerAdapterTypeEntityToReducerAdapterTypeDto( entity.getReducerAdapterType() ) );
        reducerDto.reducerOutputShaftType( reducerOutputShaftTypeEntityToReducerOutputShaftTypeDto( entity.getReducerOutputShaftType() ) );
        reducerDto.reducerInstallationType( reducerInstallationTypeEntityToReducerInstallationTypeDto( entity.getReducerInstallationType() ) );
        reducerDto.reducerMounting( reducerMountingEntityToReducerMountingDto( entity.getReducerMounting() ) );
        reducerDto.diameterInputShaft( entity.getDiameterInputShaft() );
        reducerDto.diameterOutputShaft( entity.getDiameterOutputShaft() );
        reducerDto.ratio( entity.getRatio() );
        reducerDto.torqueMoment( entity.getTorqueMoment() );

        return reducerDto.build();
    }

    @Override
    public ReducerEntity toEntity(ReducerDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReducerEntity reducerEntity = new ReducerEntity();

        reducerEntity.setIdReducer( dto.getIdReducer() );
        reducerEntity.setReducerType( reducerTypeMapper.toEntity( dto.getReducerType() ) );
        reducerEntity.setReducerSize( reducerSizeDtoToReducerSizeEntity( dto.getReducerSize() ) );
        reducerEntity.setReducerInputType( reducerInputTypeDtoToReducerInputTypeEntity( dto.getReducerInputType() ) );
        reducerEntity.setReducerAdapterType( reducerAdapterTypeDtoToReducerAdapterTypeEntity( dto.getReducerAdapterType() ) );
        reducerEntity.setReducerOutputShaftType( reducerOutputShaftTypeDtoToReducerOutputShaftTypeEntity( dto.getReducerOutputShaftType() ) );
        reducerEntity.setReducerInstallationType( reducerInstallationTypeDtoToReducerInstallationTypeEntity( dto.getReducerInstallationType() ) );
        reducerEntity.setReducerMounting( reducerMountingDtoToReducerMountingEntity( dto.getReducerMounting() ) );
        reducerEntity.setDiameterInputShaft( dto.getDiameterInputShaft() );
        reducerEntity.setDiameterOutputShaft( dto.getDiameterOutputShaft() );
        reducerEntity.setRatio( dto.getRatio() );
        reducerEntity.setTorqueMoment( dto.getTorqueMoment() );

        return reducerEntity;
    }

    protected ReducerSizeDto reducerSizeEntityToReducerSizeDto(ReducerSizeEntity reducerSizeEntity) {
        if ( reducerSizeEntity == null ) {
            return null;
        }

        ReducerSizeDto.ReducerSizeDtoBuilder reducerSizeDto = ReducerSizeDto.builder();

        reducerSizeDto.idReducerSize( reducerSizeEntity.getIdReducerSize() );
        reducerSizeDto.reducerSizeValue( reducerSizeEntity.getReducerSizeValue() );
        reducerSizeDto.reducerType( reducerTypeMapper.toDTO( reducerSizeEntity.getReducerType() ) );

        return reducerSizeDto.build();
    }

    protected ReducerInputTypeDto reducerInputTypeEntityToReducerInputTypeDto(ReducerInputTypeEntity reducerInputTypeEntity) {
        if ( reducerInputTypeEntity == null ) {
            return null;
        }

        ReducerInputTypeDto.ReducerInputTypeDtoBuilder reducerInputTypeDto = ReducerInputTypeDto.builder();

        reducerInputTypeDto.idReducerInputType( reducerInputTypeEntity.getIdReducerInputType() );
        reducerInputTypeDto.reducerInputTypeValue( reducerInputTypeEntity.getReducerInputTypeValue() );
        reducerInputTypeDto.reducerType( reducerTypeMapper.toDTO( reducerInputTypeEntity.getReducerType() ) );

        return reducerInputTypeDto.build();
    }

    protected ReducerAdapterTypeDto reducerAdapterTypeEntityToReducerAdapterTypeDto(ReducerAdapterTypeEntity reducerAdapterTypeEntity) {
        if ( reducerAdapterTypeEntity == null ) {
            return null;
        }

        ReducerAdapterTypeDto.ReducerAdapterTypeDtoBuilder reducerAdapterTypeDto = ReducerAdapterTypeDto.builder();

        reducerAdapterTypeDto.idReducerAdapterType( reducerAdapterTypeEntity.getIdReducerAdapterType() );
        reducerAdapterTypeDto.reducerAdapterTypeValue( reducerAdapterTypeEntity.getReducerAdapterTypeValue() );
        reducerAdapterTypeDto.reducerType( reducerTypeMapper.toDTO( reducerAdapterTypeEntity.getReducerType() ) );

        return reducerAdapterTypeDto.build();
    }

    protected ReducerOutputShaftTypeDto reducerOutputShaftTypeEntityToReducerOutputShaftTypeDto(ReducerOutputShaftTypeEntity reducerOutputShaftTypeEntity) {
        if ( reducerOutputShaftTypeEntity == null ) {
            return null;
        }

        ReducerOutputShaftTypeDto.ReducerOutputShaftTypeDtoBuilder reducerOutputShaftTypeDto = ReducerOutputShaftTypeDto.builder();

        reducerOutputShaftTypeDto.idReducerOutputShaftType( reducerOutputShaftTypeEntity.getIdReducerOutputShaftType() );
        reducerOutputShaftTypeDto.reducerOutputShaftTypeValue( reducerOutputShaftTypeEntity.getReducerOutputShaftTypeValue() );
        reducerOutputShaftTypeDto.reducerType( reducerTypeMapper.toDTO( reducerOutputShaftTypeEntity.getReducerType() ) );

        return reducerOutputShaftTypeDto.build();
    }

    protected ReducerInstallationTypeDto reducerInstallationTypeEntityToReducerInstallationTypeDto(ReducerInstallationTypeEntity reducerInstallationTypeEntity) {
        if ( reducerInstallationTypeEntity == null ) {
            return null;
        }

        ReducerInstallationTypeDto.ReducerInstallationTypeDtoBuilder reducerInstallationTypeDto = ReducerInstallationTypeDto.builder();

        reducerInstallationTypeDto.idReducerInstallationType( reducerInstallationTypeEntity.getIdReducerInstallationType() );
        reducerInstallationTypeDto.reducerInstallationTypeValue( reducerInstallationTypeEntity.getReducerInstallationTypeValue() );
        reducerInstallationTypeDto.reducerType( reducerTypeMapper.toDTO( reducerInstallationTypeEntity.getReducerType() ) );

        return reducerInstallationTypeDto.build();
    }

    protected ReducerMountingDto reducerMountingEntityToReducerMountingDto(ReducerMountingEntity reducerMountingEntity) {
        if ( reducerMountingEntity == null ) {
            return null;
        }

        ReducerMountingDto.ReducerMountingDtoBuilder reducerMountingDto = ReducerMountingDto.builder();

        reducerMountingDto.idReducerMounting( reducerMountingEntity.getIdReducerMounting() );
        reducerMountingDto.reducerMountingValue( reducerMountingEntity.getReducerMountingValue() );

        return reducerMountingDto.build();
    }

    protected ReducerSizeEntity reducerSizeDtoToReducerSizeEntity(ReducerSizeDto reducerSizeDto) {
        if ( reducerSizeDto == null ) {
            return null;
        }

        ReducerSizeEntity reducerSizeEntity = new ReducerSizeEntity();

        reducerSizeEntity.setIdReducerSize( reducerSizeDto.getIdReducerSize() );
        reducerSizeEntity.setReducerSizeValue( reducerSizeDto.getReducerSizeValue() );
        reducerSizeEntity.setReducerType( reducerTypeMapper.toEntity( reducerSizeDto.getReducerType() ) );

        return reducerSizeEntity;
    }

    protected ReducerInputTypeEntity reducerInputTypeDtoToReducerInputTypeEntity(ReducerInputTypeDto reducerInputTypeDto) {
        if ( reducerInputTypeDto == null ) {
            return null;
        }

        ReducerInputTypeEntity reducerInputTypeEntity = new ReducerInputTypeEntity();

        reducerInputTypeEntity.setIdReducerInputType( reducerInputTypeDto.getIdReducerInputType() );
        reducerInputTypeEntity.setReducerInputTypeValue( reducerInputTypeDto.getReducerInputTypeValue() );
        reducerInputTypeEntity.setReducerType( reducerTypeMapper.toEntity( reducerInputTypeDto.getReducerType() ) );

        return reducerInputTypeEntity;
    }

    protected ReducerAdapterTypeEntity reducerAdapterTypeDtoToReducerAdapterTypeEntity(ReducerAdapterTypeDto reducerAdapterTypeDto) {
        if ( reducerAdapterTypeDto == null ) {
            return null;
        }

        ReducerAdapterTypeEntity reducerAdapterTypeEntity = new ReducerAdapterTypeEntity();

        reducerAdapterTypeEntity.setIdReducerAdapterType( reducerAdapterTypeDto.getIdReducerAdapterType() );
        reducerAdapterTypeEntity.setReducerAdapterTypeValue( reducerAdapterTypeDto.getReducerAdapterTypeValue() );
        reducerAdapterTypeEntity.setReducerType( reducerTypeMapper.toEntity( reducerAdapterTypeDto.getReducerType() ) );

        return reducerAdapterTypeEntity;
    }

    protected ReducerOutputShaftTypeEntity reducerOutputShaftTypeDtoToReducerOutputShaftTypeEntity(ReducerOutputShaftTypeDto reducerOutputShaftTypeDto) {
        if ( reducerOutputShaftTypeDto == null ) {
            return null;
        }

        ReducerOutputShaftTypeEntity reducerOutputShaftTypeEntity = new ReducerOutputShaftTypeEntity();

        reducerOutputShaftTypeEntity.setIdReducerOutputShaftType( reducerOutputShaftTypeDto.getIdReducerOutputShaftType() );
        reducerOutputShaftTypeEntity.setReducerOutputShaftTypeValue( reducerOutputShaftTypeDto.getReducerOutputShaftTypeValue() );
        reducerOutputShaftTypeEntity.setReducerType( reducerTypeMapper.toEntity( reducerOutputShaftTypeDto.getReducerType() ) );

        return reducerOutputShaftTypeEntity;
    }

    protected ReducerInstallationTypeEntity reducerInstallationTypeDtoToReducerInstallationTypeEntity(ReducerInstallationTypeDto reducerInstallationTypeDto) {
        if ( reducerInstallationTypeDto == null ) {
            return null;
        }

        ReducerInstallationTypeEntity reducerInstallationTypeEntity = new ReducerInstallationTypeEntity();

        reducerInstallationTypeEntity.setIdReducerInstallationType( reducerInstallationTypeDto.getIdReducerInstallationType() );
        reducerInstallationTypeEntity.setReducerInstallationTypeValue( reducerInstallationTypeDto.getReducerInstallationTypeValue() );
        reducerInstallationTypeEntity.setReducerType( reducerTypeMapper.toEntity( reducerInstallationTypeDto.getReducerType() ) );

        return reducerInstallationTypeEntity;
    }

    protected ReducerMountingEntity reducerMountingDtoToReducerMountingEntity(ReducerMountingDto reducerMountingDto) {
        if ( reducerMountingDto == null ) {
            return null;
        }

        ReducerMountingEntity reducerMountingEntity = new ReducerMountingEntity();

        reducerMountingEntity.setIdReducerMounting( reducerMountingDto.getIdReducerMounting() );
        reducerMountingEntity.setReducerMountingValue( reducerMountingDto.getReducerMountingValue() );

        return reducerMountingEntity;
    }
}
