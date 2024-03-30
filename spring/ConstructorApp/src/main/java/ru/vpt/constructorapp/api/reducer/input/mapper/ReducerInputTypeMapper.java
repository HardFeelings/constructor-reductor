package ru.vpt.constructorapp.api.reducer.input.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerInputTypeMapper {
    ReducerInputTypeDto toDTO(ReducerInputTypeEntity entity);
    ReducerInputTypeEntity toEntity(ReducerInputTypeDto dto);
}
