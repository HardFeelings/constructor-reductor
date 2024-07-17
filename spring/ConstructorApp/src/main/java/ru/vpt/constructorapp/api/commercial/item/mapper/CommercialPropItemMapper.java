package ru.vpt.constructorapp.api.commercial.item.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;

@Mapper(componentModel = "spring",uses = {ProductMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommercialPropItemMapper {

    @Mapping(target = "commercialPropId", source = "commercialProp.idCommercialProp")
    CommercialPropItemDto toDTO(CommercialPropItemEntity entity);

    @Mapping(target = "commercialProp", ignore = true)
    CommercialPropItemEntity toEntity(CommercialPropItemDto dto);
}
