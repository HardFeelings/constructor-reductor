package ru.vpt.constructorapp.api.reducer.installation.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.reducer.type.mapper.ReducerTypeMapper;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;

@Mapper(componentModel = "spring", uses = {ReducerTypeMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerInstallationTypeMapper {
    ReducerInstallationTypeDto toDTO(ReducerInstallationTypeEntity entity);
    ReducerInstallationTypeEntity toEntity(ReducerInstallationTypeDto dto);
}
