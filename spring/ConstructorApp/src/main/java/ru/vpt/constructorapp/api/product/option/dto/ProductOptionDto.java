package ru.vpt.constructorapp.api.product.option.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;

@Data
@Builder
public class ProductOptionDto {
    private Long idProductOption;
    private String productOptionValue;
    private ProductTypeDto productType;
}
