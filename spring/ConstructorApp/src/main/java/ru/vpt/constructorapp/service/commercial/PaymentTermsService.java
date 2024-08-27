package ru.vpt.constructorapp.service.commercial;

import ru.vpt.constructorapp.api.commercial.terms.dto.PaymentTermsDto;
import ru.vpt.constructorapp.store.entities.commercial.PaymentTermsEntity;

import java.util.List;

public interface PaymentTermsService {

    List<PaymentTermsDto> getAll();
    PaymentTermsDto getById(Long id);
    PaymentTermsEntity findById(Long id);
    PaymentTermsDto save(PaymentTermsDto paymentTermsDto);
    Boolean delete(Long id);
}
