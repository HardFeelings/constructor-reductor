package ru.vpt.constructorapp.api.shaft.version.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.api.shaft.version.dto.ShaftVersionDto;
import ru.vpt.constructorapp.store.entities.ShaftVersionEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShaftVersionMapper {
    ShaftVersionDto toDTO(ShaftVersionEntity entity);
    ShaftVersionEntity toEntity(ShaftVersionDto dto);
}
