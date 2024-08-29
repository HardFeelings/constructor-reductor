package ru.vpt.constructorapp.api.commercial.payment.dto;

import lombok.*;
import ru.vpt.constructorapp.api.commercial.terms.dto.PaymentTermsDto;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommercialPropTermsDto {
    private Long idCommercialPropTerms;
    private Integer ord;
    private Double percent;
    private Integer days;
    private Long commercialPropId;
    private PaymentTermsDto paymentTerms;
}
