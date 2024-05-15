package ru.vpt.constructorapp.api.reducer.size.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerSizeMapper {

    @Mapping(source = "reducerType.idReducerType", target = "reducerTypeId")
    ReducerSizeDto toDTO(ReducerSizeEntity entity);

    @Mapping(target = "reducerType", ignore = true)
    ReducerSizeEntity toEntity(ReducerSizeDto dto);
}
