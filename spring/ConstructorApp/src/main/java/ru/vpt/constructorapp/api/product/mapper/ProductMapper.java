package ru.vpt.constructorapp.api.product.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.input.node.mapper.InputNodeMapper;
import ru.vpt.constructorapp.api.product.dto.ProductDto;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.store.entities.ProductEntity;

@Mapper(componentModel = "spring", uses = {ReducerMapper.class, InputNodeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {
    ProductDto toDTO(ProductEntity entity);
    ProductEntity toEntity(ProductDto dto);
}