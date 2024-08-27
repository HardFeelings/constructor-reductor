package ru.vpt.constructorapp.api.commercial.terms.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PaymentTermsDto {
    private Long idPaymentTerms;
    private String visibleName;
    private String fullName;
}
