package ru.vpt.constructorapp.api.product.option.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOptionDto {
    private Long idProductOption;
    private String productOptionValue;
    private Long productTypeId;
}
