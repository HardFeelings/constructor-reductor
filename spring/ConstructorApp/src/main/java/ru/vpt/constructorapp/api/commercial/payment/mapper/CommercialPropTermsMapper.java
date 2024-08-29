package ru.vpt.constructorapp.api.commercial.payment.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;
import ru.vpt.constructorapp.api.commercial.terms.mapper.PaymentTermsMapper;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropTermsEntity;

@Mapper(componentModel = "spring",uses = {PaymentTermsMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommercialPropTermsMapper {

    @Mapping(target = "commercialPropId", source = "commercialProp.idCommercialProp")
    CommercialPropTermsDto toDTO(CommercialPropTermsEntity entity);

    @Mapping(target = "commercialProp", ignore = true)
    @Mapping(target = "paymentTerms", ignore = true)
    CommercialPropTermsEntity toEntity(CommercialPropTermsDto dto);
}
