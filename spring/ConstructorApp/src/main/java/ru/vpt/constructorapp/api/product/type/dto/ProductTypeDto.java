package ru.vpt.constructorapp.api.product.type.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductTypeDto {
    private Long idProductType;
    private String productTypeValue;
}
