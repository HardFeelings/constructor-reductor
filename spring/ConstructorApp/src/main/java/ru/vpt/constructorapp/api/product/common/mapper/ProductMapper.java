package ru.vpt.constructorapp.api.product.common.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.vpt.constructorapp.api.motor.common.mapper.MotorMapper;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.option.mapper.ProductOptionMapper;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

import java.util.Base64;

@Mapper(componentModel = "spring",uses = {ProductTypeMapper.class, ReducerMapper.class,
        MotorMapper.class, ProductOptionMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {



    @Mapping(target = "productTypeId", source = "productType.idProductType")
    @Mapping(target = "reducerId", source = "reducer.idReducer")
    @Mapping(target = "motorId", source = "motor.idMotor")
    @Mapping(target = "optionsIds", source = "options")
    @Mapping(target = "imageEmpty", source = "imageEmpty")
    @Mapping(target = "reducer", ignore = true)
    @Mapping(target = "motor", ignore = true)
    @Mapping(target = "imageString", ignore = true)
    ProductDto toDTO(ProductEntity entity);


    @Named(value = "toDTOWithRef")
    @Mapping(target = "productTypeId", source = "productType.idProductType")
    @Mapping(target = "reducerId", source = "reducer.idReducer")
    @Mapping(target = "motorId", source = "motor.idMotor")
    @Mapping(target = "optionsIds", source = "options")
    @Mapping(target = "imageString", ignore = true)
    ProductDto toDTOWithRef(ProductEntity entity);


    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "reducer", ignore = true)
    @Mapping(target = "options", ignore = true)
    @Mapping(target = "motor", ignore = true)
    @Mapping(target = "productImage", ignore = true)
    ProductEntity toEntity(ProductDto dto);

    default Long toId(ProductOptionEntity entity){
        return entity.getIdProductOption();
    }
}

