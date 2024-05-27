package ru.vpt.constructorapp.api.product.common.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.motor.common.mapper.MotorMapper;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.option.mapper.ProductOptionMapper;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

@Mapper(componentModel = "spring",uses = {ProductTypeMapper.class, ReducerMapper.class,
        MotorMapper.class, ProductOptionMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    ProductDto toDTO(ProductEntity entity);

    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "reducer", ignore = true)
    @Mapping(target = "options", ignore = true)
    @Mapping(target = "motor", ignore = true)
    ProductEntity toEntity(ProductDto dto);
}

