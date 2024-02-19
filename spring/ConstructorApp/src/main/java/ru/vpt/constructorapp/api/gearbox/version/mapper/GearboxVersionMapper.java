package ru.vpt.constructorapp.api.gearbox.version.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.gearbox.version.dto.GearboxVersionDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.api.shaft.version.dto.ShaftVersionDto;
import ru.vpt.constructorapp.store.entities.GearboxVersionEntity;
import ru.vpt.constructorapp.store.entities.ShaftVersionEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GearboxVersionMapper {
    GearboxVersionDto toDTO(GearboxVersionEntity entity);
    GearboxVersionEntity toEntity(GearboxVersionDto dto);
}
