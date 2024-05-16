package ru.vpt.constructorapp.api.product.option.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

@Mapper(componentModel = "spring",uses = {ProductTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductOptionMapper {

    @Mapping(source = "productType.idProductType", target = "productTypeId")
    ProductOptionDto toDTO(ProductOptionEntity entity);

    @Mapping(target = "productType", ignore = true)
    ProductOptionEntity toEntity(ProductOptionDto dto);
}
