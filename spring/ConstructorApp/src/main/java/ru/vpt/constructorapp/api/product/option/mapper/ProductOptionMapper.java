package ru.vpt.constructorapp.api.product.option.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

@Mapper(componentModel = "spring",uses = {ProductTypeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductOptionMapper {
    ProductOptionDto toDTO(ProductOptionEntity entity);
    ProductOptionEntity toEntity(ProductOptionDto dto);
}
