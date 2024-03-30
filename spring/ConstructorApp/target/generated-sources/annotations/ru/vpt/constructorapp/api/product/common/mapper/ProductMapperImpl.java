package ru.vpt.constructorapp.api.product.common.mapper;

import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.motor.common.mapper.MotorMapper;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.option.mapper.ProductOptionMapper;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-31T01:20:01+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    private final ProductTypeMapper productTypeMapper;
    private final ReducerMapper reducerMapper;
    private final MotorMapper motorMapper;
    private final ProductOptionMapper productOptionMapper;

    @Autowired
    public ProductMapperImpl(ProductTypeMapper productTypeMapper, ReducerMapper reducerMapper, MotorMapper motorMapper, ProductOptionMapper productOptionMapper) {

        this.productTypeMapper = productTypeMapper;
        this.reducerMapper = reducerMapper;
        this.motorMapper = motorMapper;
        this.productOptionMapper = productOptionMapper;
    }

    @Override
    public ProductDto toDTO(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.idProduct( entity.getIdProduct() );
        productDto.productType( productTypeMapper.toDTO( entity.getProductType() ) );
        productDto.weight( entity.getWeight() );
        productDto.price( entity.getPrice() );
        productDto.reducer( reducerMapper.toDTO( entity.getReducer() ) );
        productDto.motor( motorMapper.toDTO( entity.getMotor() ) );
        productDto.productOption( productOptionMapper.toDTO( entity.getProductOption() ) );

        return productDto.build();
    }

    @Override
    public ProductEntity toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setIdProduct( dto.getIdProduct() );
        productEntity.setProductType( productTypeMapper.toEntity( dto.getProductType() ) );
        productEntity.setWeight( dto.getWeight() );
        productEntity.setPrice( dto.getPrice() );
        productEntity.setReducer( reducerMapper.toEntity( dto.getReducer() ) );
        productEntity.setMotor( motorMapper.toEntity( dto.getMotor() ) );
        productEntity.setProductOption( productOptionMapper.toEntity( dto.getProductOption() ) );

        return productEntity;
    }
}
