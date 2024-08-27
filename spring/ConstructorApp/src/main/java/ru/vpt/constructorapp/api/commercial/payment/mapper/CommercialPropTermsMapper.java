package ru.vpt.constructorapp.api.commercial.payment.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropTermsEntity;

@Mapper(componentModel = "spring",uses = {ProductMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommercialPropTermsMapper {

    @Mapping(target = "commercialPropId", source = "commercialProp.idCommercialProp")
    @Mapping(target = "paymentTermsId", source = "paymentTerms.idPaymentTerms")
    CommercialPropTermsDto toDTO(CommercialPropTermsEntity entity);

    @Mapping(target = "commercialProp", ignore = true)
    @Mapping(target = "paymentTerms", ignore = true)
    CommercialPropTermsEntity toEntity(CommercialPropTermsDto dto);
}
