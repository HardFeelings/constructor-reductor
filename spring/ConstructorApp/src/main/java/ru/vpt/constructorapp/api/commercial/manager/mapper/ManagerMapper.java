package ru.vpt.constructorapp.api.commercial.manager.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ManagerMapper {

    ManagerDto toDTO(ManagerEntity entity);

    ManagerEntity toEntity(ManagerDto dto);
}
