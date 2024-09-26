package ru.vpt.constructorapp.api.commercial.terms.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.vpt.constructorapp.api.commercial.terms.dto.PaymentTermsDto;
import ru.vpt.constructorapp.store.entities.commercial.PaymentTermsEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T22:20:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class PaymentTermsMapperImpl implements PaymentTermsMapper {

    @Override
    public PaymentTermsDto toDTO(PaymentTermsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PaymentTermsDto.PaymentTermsDtoBuilder paymentTermsDto = PaymentTermsDto.builder();

        paymentTermsDto.idPaymentTerms( entity.getIdPaymentTerms() );
        paymentTermsDto.visibleName( entity.getVisibleName() );
        paymentTermsDto.fullName( entity.getFullName() );

        return paymentTermsDto.build();
    }

    @Override
    public PaymentTermsEntity toEntity(PaymentTermsDto dto) {
        if ( dto == null ) {
            return null;
        }

        PaymentTermsEntity paymentTermsEntity = new PaymentTermsEntity();

        paymentTermsEntity.setIdPaymentTerms( dto.getIdPaymentTerms() );
        paymentTermsEntity.setVisibleName( dto.getVisibleName() );
        paymentTermsEntity.setFullName( dto.getFullName() );

        return paymentTermsEntity;
    }
}
