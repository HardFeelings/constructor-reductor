package ru.vpt.constructorapp.api.commercial.item.dto;

import lombok.*;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommercialPropItemDto {
    private Long idCommercialPropItem;
    private Long commercialPropId;
    private ProductDto product;
    private Integer amount;
}
