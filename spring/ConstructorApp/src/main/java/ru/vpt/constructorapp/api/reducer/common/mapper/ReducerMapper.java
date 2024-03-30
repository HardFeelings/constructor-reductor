package ru.vpt.constructorapp.api.reducer.common.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerMapper {
    ReducerDto toDTO(ReducerEntity entity);
    ReducerEntity toEntity(ReducerDto dto);
}
