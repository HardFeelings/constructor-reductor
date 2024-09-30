package ru.vpt.constructorapp.api.commercial.manager.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ManagerMapper {

    @Mapping(target = "idUser", source = "employee.idEmployee")
    ManagerDto toDTO(ManagerEntity entity);

    @Mapping(target = "employee", ignore = true)
    ManagerEntity toEntity(ManagerDto dto);
}
