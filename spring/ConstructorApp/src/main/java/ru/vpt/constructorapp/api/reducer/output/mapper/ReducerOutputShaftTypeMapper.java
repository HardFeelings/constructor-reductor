package ru.vpt.constructorapp.api.reducer.output.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerOutputShaftTypeMapper {

    @Mapping(source = "reducerType.idReducerType", target = "reducerTypeId")
    ReducerOutputShaftTypeDto toDTO(ReducerOutputShaftTypeEntity entity);

    @Mapping(target = "reducerType", ignore = true)
    ReducerOutputShaftTypeEntity toEntity(ReducerOutputShaftTypeDto dto);
}
