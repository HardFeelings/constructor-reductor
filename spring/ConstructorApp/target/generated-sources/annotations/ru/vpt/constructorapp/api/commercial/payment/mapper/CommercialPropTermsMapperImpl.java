package ru.vpt.constructorapp.api.commercial.payment.mapper;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;
import ru.vpt.constructorapp.api.commercial.terms.mapper.PaymentTermsMapper;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropTermsEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class CommercialPropTermsMapperImpl implements CommercialPropTermsMapper {

    private final PaymentTermsMapper paymentTermsMapper;

    @Autowired
    public CommercialPropTermsMapperImpl(PaymentTermsMapper paymentTermsMapper) {

        this.paymentTermsMapper = paymentTermsMapper;
    }

    @Override
    public CommercialPropTermsDto toDTO(CommercialPropTermsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CommercialPropTermsDto.CommercialPropTermsDtoBuilder commercialPropTermsDto = CommercialPropTermsDto.builder();

        commercialPropTermsDto.commercialPropId( entityCommercialPropIdCommercialProp( entity ) );
        commercialPropTermsDto.idCommercialPropTerms( entity.getIdCommercialPropTerms() );
        commercialPropTermsDto.ord( entity.getOrd() );
        commercialPropTermsDto.percent( entity.getPercent() );
        commercialPropTermsDto.days( entity.getDays() );
        commercialPropTermsDto.paymentTerms( paymentTermsMapper.toDTO( entity.getPaymentTerms() ) );

        return commercialPropTermsDto.build();
    }

    @Override
    public CommercialPropTermsEntity toEntity(CommercialPropTermsDto dto) {
        if ( dto == null ) {
            return null;
        }

        CommercialPropTermsEntity commercialPropTermsEntity = new CommercialPropTermsEntity();

        commercialPropTermsEntity.setIdCommercialPropTerms( dto.getIdCommercialPropTerms() );
        commercialPropTermsEntity.setOrd( dto.getOrd() );
        commercialPropTermsEntity.setPercent( dto.getPercent() );
        commercialPropTermsEntity.setDays( dto.getDays() );

        return commercialPropTermsEntity;
    }

    private Long entityCommercialPropIdCommercialProp(CommercialPropTermsEntity commercialPropTermsEntity) {
        if ( commercialPropTermsEntity == null ) {
            return null;
        }
        CommercialPropEntity commercialProp = commercialPropTermsEntity.getCommercialProp();
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
