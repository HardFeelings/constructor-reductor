package ru.vpt.constructorapp.api.commercial.prop.dto;

import lombok.*;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommercialPropDto {
    private Long idCommercialProp;
    private String number;
    private String partner;
    private BigDecimal cost;
    private String timestamp;
    private ManagerEntity manager;
    private Integer deliveryTime;
    private Integer guarantee;
    private String deliveryTerms;
    private List<CommercialPropItemDto> commercialPropItems;
    private List<CommercialPropTermsDto> commercialPropTerms;
}
