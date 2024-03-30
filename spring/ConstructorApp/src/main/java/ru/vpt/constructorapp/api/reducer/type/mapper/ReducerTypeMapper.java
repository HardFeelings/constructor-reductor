package ru.vpt.constructorapp.api.reducer.type.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerTypeMapper {
    ReducerTypeDto toDTO(ReducerTypeEntity entity);
    ReducerTypeEntity toEntity(ReducerTypeDto dto);
}
