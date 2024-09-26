package ru.vpt.constructorapp.api.product.common.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.motor.common.mapper.MotorMapper;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    private final ReducerMapper reducerMapper;
    private final MotorMapper motorMapper;

    @Autowired
    public ProductMapperImpl(ReducerMapper reducerMapper, MotorMapper motorMapper) {

        this.reducerMapper = reducerMapper;
        this.motorMapper = motorMapper;
    }

    @Override
    public ProductDto toDTO(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.productTypeId( entityProductTypeIdProductType( entity ) );
        productDto.reducerId( entityReducerIdReducer( entity ) );
        productDto.motorId( entityMotorIdMotor( entity ) );
        productDto.optionsIds( productOptionEntitySetToLongSet( entity.getOptions() ) );
        productDto.imageEmpty( entity.getImageEmpty() );
        productDto.idProduct( entity.getIdProduct() );
        productDto.name( entity.getName() );
        productDto.weight( entity.getWeight() );
        productDto.price( entity.getPrice() );
        productDto.rpm( entity.getRpm() );
        productDto.torqueMoment( entity.getTorqueMoment() );
        productDto.serviceFactor( entity.getServiceFactor() );

        return productDto.build();
    }

    @Override
    public ProductDto toDTOWithRef(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.productTypeId( entityProductTypeIdProductType( entity ) );
        productDto.reducerId( entityReducerIdReducer( entity ) );
        productDto.motorId( entityMotorIdMotor( entity ) );
        productDto.optionsIds( productOptionEntitySetToLongSet( entity.getOptions() ) );
        productDto.idProduct( entity.getIdProduct() );
        productDto.name( entity.getName() );
        productDto.weight( entity.getWeight() );
        productDto.price( entity.getPrice() );
        productDto.rpm( entity.getRpm() );
        productDto.torqueMoment( entity.getTorqueMoment() );
        productDto.serviceFactor( entity.getServiceFactor() );
        productDto.reducer( reducerMapper.toDTO( entity.getReducer() ) );
        productDto.motor( motorMapper.toDTO( entity.getMotor() ) );
        productDto.imageEmpty( entity.getImageEmpty() );

        return productDto.build();
    }

    @Override
    public ProductEntity toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setIdProduct( dto.getIdProduct() );
        productEntity.setName( dto.getName() );
        productEntity.setWeight( dto.getWeight() );
        productEntity.setPrice( dto.getPrice() );
        productEntity.setRpm( dto.getRpm() );
        productEntity.setTorqueMoment( dto.getTorqueMoment() );
        productEntity.setServiceFactor( dto.getServiceFactor() );

        return productEntity;
    }

    private Long entityProductTypeIdProductType(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }
        ProductTypeEntity productType = productEntity.getProductType();
        if ( productType == null ) {
            return null;
        }
        Long idProductType = productType.getIdProductType();
        if ( idProductType == null ) {
            return null;
        }
        return idProductType;
    }

    private Long entityReducerIdReducer(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }
        ReducerEntity reducer = productEntity.getReducer();
        if ( reducer == null ) {
            return null;
        }
        Long idReducer = reducer.getIdReducer();
        if ( idReducer == null ) {
            return null;
        }
        return idReducer;
    }

    private Long entityMotorIdMotor(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }
        MotorEntity motor = productEntity.getMotor();
        if ( motor == null ) {
            return null;
        }
        Long idMotor = motor.getIdMotor();
        if ( idMotor == null ) {
            return null;
        }
        return idMotor;
    }

    protected Set<Long> productOptionEntitySetToLongSet(Set<ProductOptionEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Long> set1 = new LinkedHashSet<Long>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProductOptionEntity productOptionEntity : set ) {
            set1.add( toId( productOptionEntity ) );
        }

        return set1;
    }
}
