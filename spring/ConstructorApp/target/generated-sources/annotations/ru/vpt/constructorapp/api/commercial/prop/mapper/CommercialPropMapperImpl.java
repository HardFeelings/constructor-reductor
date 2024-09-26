package ru.vpt.constructorapp.api.commercial.prop.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.commercial.item.mapper.CommercialPropItemMapper;
import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.commercial.terms.dto.PaymentTermsDto;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropTermsEntity;
import ru.vpt.constructorapp.store.entities.commercial.PaymentTermsEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class CommercialPropMapperImpl implements CommercialPropMapper {

    private final CommercialPropItemMapper commercialPropItemMapper;

    @Autowired
    public CommercialPropMapperImpl(CommercialPropItemMapper commercialPropItemMapper) {

        this.commercialPropItemMapper = commercialPropItemMapper;
    }

    @Override
    public CommercialPropDto toDTO(CommercialPropEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CommercialPropDto.CommercialPropDtoBuilder commercialPropDto = CommercialPropDto.builder();

        commercialPropDto.idCommercialProp( entity.getIdCommercialProp() );
        commercialPropDto.number( entity.getNumber() );
        commercialPropDto.partner( entity.getPartner() );
        commercialPropDto.cost( entity.getCost() );
        commercialPropDto.timestamp( entity.getTimestamp() );
        commercialPropDto.manager( entity.getManager() );
        commercialPropDto.deliveryTime( entity.getDeliveryTime() );
        commercialPropDto.guarantee( entity.getGuarantee() );
        commercialPropDto.deliveryTerms( entity.getDeliveryTerms() );
        commercialPropDto.commercialPropItems( commercialPropItemEntityListToCommercialPropItemDtoList( entity.getCommercialPropItems() ) );
        commercialPropDto.commercialPropTerms( commercialPropTermsEntityListToCommercialPropTermsDtoList( entity.getCommercialPropTerms() ) );

        return commercialPropDto.build();
    }

    @Override
    public CommercialPropDto toDTOWithoutItems(CommercialPropEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CommercialPropDto.CommercialPropDtoBuilder commercialPropDto = CommercialPropDto.builder();

        commercialPropDto.idCommercialProp( entity.getIdCommercialProp() );
        commercialPropDto.number( entity.getNumber() );
        commercialPropDto.partner( entity.getPartner() );
        commercialPropDto.cost( entity.getCost() );
        commercialPropDto.timestamp( entity.getTimestamp() );
        commercialPropDto.manager( entity.getManager() );
        commercialPropDto.deliveryTime( entity.getDeliveryTime() );
        commercialPropDto.guarantee( entity.getGuarantee() );
        commercialPropDto.deliveryTerms( entity.getDeliveryTerms() );

        return commercialPropDto.build();
    }

    @Override
    public CommercialPropEntity toEntity(CommercialPropDto dto) {
        if ( dto == null ) {
            return null;
        }

        CommercialPropEntity commercialPropEntity = new CommercialPropEntity();

        commercialPropEntity.setIdCommercialProp( dto.getIdCommercialProp() );
        commercialPropEntity.setNumber( dto.getNumber() );
        commercialPropEntity.setPartner( dto.getPartner() );
        commercialPropEntity.setCost( dto.getCost() );
        commercialPropEntity.setDeliveryTime( dto.getDeliveryTime() );
        commercialPropEntity.setGuarantee( dto.getGuarantee() );
        commercialPropEntity.setDeliveryTerms( dto.getDeliveryTerms() );
        commercialPropEntity.setTimestamp( dto.getTimestamp() );
        commercialPropEntity.setManager( dto.getManager() );

        return commercialPropEntity;
    }

    protected List<CommercialPropItemDto> commercialPropItemEntityListToCommercialPropItemDtoList(List<CommercialPropItemEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CommercialPropItemDto> list1 = new ArrayList<CommercialPropItemDto>( list.size() );
        for ( CommercialPropItemEntity commercialPropItemEntity : list ) {
            list1.add( commercialPropItemMapper.toDTO( commercialPropItemEntity ) );
        }

        return list1;
    }

    protected PaymentTermsDto paymentTermsEntityToPaymentTermsDto(PaymentTermsEntity paymentTermsEntity) {
        if ( paymentTermsEntity == null ) {
            return null;
        }

        PaymentTermsDto.PaymentTermsDtoBuilder paymentTermsDto = PaymentTermsDto.builder();

        paymentTermsDto.idPaymentTerms( paymentTermsEntity.getIdPaymentTerms() );
        paymentTermsDto.visibleName( paymentTermsEntity.getVisibleName() );
        paymentTermsDto.fullName( paymentTermsEntity.getFullName() );

        return paymentTermsDto.build();
    }

    protected CommercialPropTermsDto commercialPropTermsEntityToCommercialPropTermsDto(CommercialPropTermsEntity commercialPropTermsEntity) {
        if ( commercialPropTermsEntity == null ) {
            return null;
        }

        CommercialPropTermsDto.CommercialPropTermsDtoBuilder commercialPropTermsDto = CommercialPropTermsDto.builder();

        commercialPropTermsDto.idCommercialPropTerms( commercialPropTermsEntity.getIdCommercialPropTerms() );
        commercialPropTermsDto.ord( commercialPropTermsEntity.getOrd() );
        commercialPropTermsDto.percent( commercialPropTermsEntity.getPercent() );
        commercialPropTermsDto.days( commercialPropTermsEntity.getDays() );
        commercialPropTermsDto.paymentTerms( paymentTermsEntityToPaymentTermsDto( commercialPropTermsEntity.getPaymentTerms() ) );

        return commercialPropTermsDto.build();
    }

    protected List<CommercialPropTermsDto> commercialPropTermsEntityListToCommercialPropTermsDtoList(List<CommercialPropTermsEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CommercialPropTermsDto> list1 = new ArrayList<CommercialPropTermsDto>( list.size() );
        for ( CommercialPropTermsEntity commercialPropTermsEntity : list ) {
            list1.add( commercialPropTermsEntityToCommercialPropTermsDto( commercialPropTermsEntity ) );
        }

        return list1;
    }
}
