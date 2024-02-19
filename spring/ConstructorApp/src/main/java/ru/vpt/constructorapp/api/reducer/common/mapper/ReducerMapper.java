package ru.vpt.constructorapp.api.reducer.common.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.gearbox.version.mapper.GearboxVersionMapper;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.shaft.version.mapper.ShaftVersionMapper;
import ru.vpt.constructorapp.store.entities.ReducerEntity;

@Mapper(componentModel = "spring", uses = {GearboxVersionMapper.class, ShaftVersionMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerMapper {
    ReducerDto toDTO(ReducerEntity entity);
    ReducerEntity toEntity(ReducerDto dto);
}
