package ru.vpt.constructorapp.api.product.type.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductTypeDto {
    private Long idProductType;
    private String productTypeValue;
}
