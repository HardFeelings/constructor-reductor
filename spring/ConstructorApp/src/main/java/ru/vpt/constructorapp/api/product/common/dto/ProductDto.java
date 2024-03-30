package ru.vpt.constructorapp.api.product.common.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;

@Data
@Builder
public class ProductDto {
    private Long idProduct;
    private ProductTypeDto productType;
    private Double weight;
    private Double price;
    private ReducerDto reducer;
    private MotorDto motor;
    private ProductOptionDto productOption;
}
