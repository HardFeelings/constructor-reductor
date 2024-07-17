package ru.vpt.constructorapp.api.commercial.prop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.vpt.constructorapp.api.commercial.item.mapper.CommercialPropItemMapper;
import ru.vpt.constructorapp.api.commercial.manager.mapper.ManagerMapper;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

@Mapper(componentModel = "spring",uses = {CommercialPropItemMapper.class, ManagerMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommercialPropMapper {

    CommercialPropDto toDTO(CommercialPropEntity entity);
    @Named(value = "withoutItems")
    @Mapping(target = "commercialPropItems", ignore = true)
    CommercialPropDto toDTOWithoutItems(CommercialPropEntity entity);
    @Mapping(target = "commercialPropItems",ignore = true)
    CommercialPropEntity toEntity(CommercialPropDto dto);
}
