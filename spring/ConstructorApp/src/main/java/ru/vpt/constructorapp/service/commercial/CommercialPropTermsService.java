package ru.vpt.constructorapp.service.commercial;

import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;

import java.util.List;

public interface CommercialPropTermsService {
    List<CommercialPropTermsDto> getAllByCommercialPropId(Long id);

    Boolean delete(Long id);

    CommercialPropTermsDto save(CommercialPropTermsDto commercialPropTermsDto, Long id);
}
