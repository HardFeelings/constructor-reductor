package ru.vpt.constructorapp.api.product.option.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ProductOptionMapperImpl implements ProductOptionMapper {

    @Override
    public ProductOptionDto toDTO(ProductOptionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductOptionDto.ProductOptionDtoBuilder productOptionDto = ProductOptionDto.builder();

        productOptionDto.productTypeId( entityProductTypeIdProductType( entity ) );
        productOptionDto.idProductOption( entity.getIdProductOption() );
        productOptionDto.productOptionValue( entity.getProductOptionValue() );

        return productOptionDto.build();
    }

    @Override
    public ProductOptionEntity toEntity(ProductOptionDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductOptionEntity productOptionEntity = new ProductOptionEntity();

        productOptionEntity.setIdProductOption( dto.getIdProductOption() );
        productOptionEntity.setProductOptionValue( dto.getProductOptionValue() );

        return productOptionEntity;
    }

    private Long entityProductTypeIdProductType(ProductOptionEntity productOptionEntity) {
        if ( productOptionEntity == null ) {
            return null;
        }
        ProductTypeEntity productType = productOptionEntity.getProductType();
        if ( productType == null ) {
            return null;
        }
        Long idProductType = productType.getIdProductType();
        if ( idProductType == null ) {
            return null;
        }
        return idProductType;
    }
}
