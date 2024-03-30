package ru.vpt.constructorapp.api.reducer.mounting.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReducerMountingMapper {
    ReducerMountingDto toDTO(ReducerMountingEntity entity);
    ReducerMountingEntity toEntity(ReducerMountingDto dto);
}
