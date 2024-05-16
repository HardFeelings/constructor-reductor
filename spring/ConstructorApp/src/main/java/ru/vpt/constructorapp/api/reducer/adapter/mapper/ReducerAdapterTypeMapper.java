package ru.vpt.constructorapp.api.reducer.adapter.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.reducer.input.mapper.ReducerInputTypeMapper;
import ru.vpt.constructorapp.api.reducer.installation.mapper.ReducerInstallationTypeMapper;
import ru.vpt.constructorapp.api.reducer.mounting.mapper.ReducerMountingMapper;
import ru.vpt.constructorapp.api.reducer.output.mapper.ReducerOutputShaftTypeMapper;
import ru.vpt.constructorapp.api.reducer.size.mapper.ReducerSizeMapper;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerAdapterTypeEntity;

@Mapper(componentModel = "spring", uses = {
        ReducerTypeMapper.class, ReducerSizeMapper.class, ReducerInputTypeMapper.class, ReducerAdapterTypeMapper.class,
        ReducerOutputShaftTypeMapper.class, ReducerInstallationTypeMapper.class, ReducerMountingMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerAdapterTypeMapper {

    @Mapping(source = "reducerType.idReducerType", target = "reducerTypeId")
    ReducerAdapterTypeDto toDTO(ReducerAdapterTypeEntity entity);

    @Mapping(target = "reducerType", ignore = true)
    ReducerAdapterTypeEntity toEntity(ReducerAdapterTypeDto dto);
}

