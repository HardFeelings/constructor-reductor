package ru.vpt.constructorapp.api.commercial.item.mapper;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class CommercialPropItemMapperImpl implements CommercialPropItemMapper {

    private final ProductMapper productMapper;

    @Autowired
    public CommercialPropItemMapperImpl(ProductMapper productMapper) {

        this.productMapper = productMapper;
    }

    @Override
    public CommercialPropItemDto toDTO(CommercialPropItemEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CommercialPropItemDto.CommercialPropItemDtoBuilder commercialPropItemDto = CommercialPropItemDto.builder();

        commercialPropItemDto.commercialPropId( entityCommercialPropIdCommercialProp( entity ) );
        commercialPropItemDto.idCommercialPropItem( entity.getIdCommercialPropItem() );
        commercialPropItemDto.product( productMapper.toDTO( entity.getProduct() ) );
        commercialPropItemDto.amount( entity.getAmount() );

        return commercialPropItemDto.build();
    }

    @Override
    public CommercialPropItemEntity toEntity(CommercialPropItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        CommercialPropItemEntity commercialPropItemEntity = new CommercialPropItemEntity();

        commercialPropItemEntity.setIdCommercialPropItem( dto.getIdCommercialPropItem() );
        commercialPropItemEntity.setProduct( productMapper.toEntity( dto.getProduct() ) );
        commercialPropItemEntity.setAmount( dto.getAmount() );

        return commercialPropItemEntity;
    }

    private Long entityCommercialPropIdCommercialProp(CommercialPropItemEntity commercialPropItemEntity) {
        if ( commercialPropItemEntity == null ) {
            return null;
        }
        CommercialPropEntity commercialProp = commercialPropItemEntity.getCommercialProp();
        if ( commercialProp == null ) {
            return null;
        }
        Long idCommercialProp = commercialProp.getIdCommercialProp();
        if ( idCommercialProp == null ) {
            return null;
        }
        return idCommercialProp;
    }
}
