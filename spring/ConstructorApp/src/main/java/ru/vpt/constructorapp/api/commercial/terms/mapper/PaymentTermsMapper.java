package ru.vpt.constructorapp.api.commercial.terms.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.vpt.constructorapp.api.commercial.terms.dto.PaymentTermsDto;
import ru.vpt.constructorapp.store.entities.commercial.PaymentTermsEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PaymentTermsMapper {
    PaymentTermsDto toDTO(PaymentTermsEntity entity);

    PaymentTermsEntity toEntity(PaymentTermsDto dto);
}
