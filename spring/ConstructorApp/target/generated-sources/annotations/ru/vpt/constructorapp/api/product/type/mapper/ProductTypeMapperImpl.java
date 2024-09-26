package ru.vpt.constructorapp.api.product.type.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ProductTypeMapperImpl implements ProductTypeMapper {

    @Override
    public ProductTypeDto toDTO(ProductTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductTypeDto.ProductTypeDtoBuilder productTypeDto = ProductTypeDto.builder();

        productTypeDto.idProductType( entity.getIdProductType() );
        productTypeDto.productTypeValue( entity.getProductTypeValue() );

        return productTypeDto.build();
    }

    @Override
    public ProductTypeEntity toEntity(ProductTypeDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductTypeEntity productTypeEntity = new ProductTypeEntity();

        productTypeEntity.setIdProductType( dto.getIdProductType() );
        productTypeEntity.setProductTypeValue( dto.getProductTypeValue() );

        return productTypeEntity;
    }
}
