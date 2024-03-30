package ru.vpt.constructorapp.api.product.type.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductTypeMapper {
    ProductTypeDto toDTO(ProductTypeEntity entity);
    ProductTypeEntity toEntity(ProductTypeDto dto);
}
