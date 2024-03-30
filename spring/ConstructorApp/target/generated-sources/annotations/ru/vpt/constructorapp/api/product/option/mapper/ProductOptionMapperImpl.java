package ru.vpt.constructorapp.api.product.option.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-31T01:05:19+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ProductOptionMapperImpl implements ProductOptionMapper {

    private final ProductTypeMapper productTypeMapper;

    @Autowired
    public ProductOptionMapperImpl(ProductTypeMapper productTypeMapper) {

        this.productTypeMapper = productTypeMapper;
    }

    @Override
    public ProductOptionDto toDTO(ProductOptionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductOptionDto.ProductOptionDtoBuilder productOptionDto = ProductOptionDto.builder();

        productOptionDto.idProductOption( entity.getIdProductOption() );
        productOptionDto.productOptionValue( entity.getProductOptionValue() );
        productOptionDto.productType( productTypeMapper.toDTO( entity.getProductType() ) );

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
        productOptionEntity.setProductType( productTypeMapper.toEntity( dto.getProductType() ) );

        return productOptionEntity;
    }
}
