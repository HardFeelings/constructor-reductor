package ru.vpt.constructorapp.api.reducer.size.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerSizeMapper {
    ReducerSizeDto toDTO(ReducerSizeEntity entity);
    ReducerSizeEntity toEntity(ReducerSizeDto dto);
}
