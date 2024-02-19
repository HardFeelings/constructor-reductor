package ru.vpt.constructorapp.api.input.node.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.input.node.dto.InputNodeDto;
import ru.vpt.constructorapp.api.shaft.version.dto.ShaftVersionDto;
import ru.vpt.constructorapp.store.entities.InputNodeEntity;
import ru.vpt.constructorapp.store.entities.ShaftVersionEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InputNodeMapper {
    InputNodeDto toDTO(InputNodeEntity entity);
    InputNodeEntity toEntity(InputNodeDto dto);
}
