package ru.vpt.constructorapp.api.product.option.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductOptionDto {
    private Long idProductOption;
    private String productOptionValue;
    private Long productTypeId;
}
