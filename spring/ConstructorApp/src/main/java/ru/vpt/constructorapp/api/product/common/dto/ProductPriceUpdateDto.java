package ru.vpt.constructorapp.api.product.common.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductPriceUpdateDto {
    private String prefix;
    private Double percent;
}
