package ru.vpt.constructorapp.api.reducer.common.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerMapper {

    ReducerDto toDTO(ReducerEntity entity);

    @Mapping(target = "reducerType", ignore = true)
    @Mapping(target = "reducerSize", ignore = true)
    @Mapping(target = "reducerInputType", ignore = true)
    @Mapping(target = "reducerAdapterType", ignore = true)
    @Mapping(target = "reducerOutputShaftType", ignore = true)
    @Mapping(target = "reducerInstallationType", ignore = true)
    @Mapping(target = "reducerMounting", ignore = true)
    ReducerEntity toEntity(ReducerDto dto);
}
