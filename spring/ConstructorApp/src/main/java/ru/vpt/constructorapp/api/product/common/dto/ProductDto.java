package ru.vpt.constructorapp.api.product.common.dto;

import lombok.*;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;

import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductDto {
    private Long idProduct;
    private Long productTypeId;
    //private ProductTypeDto productType;
    private String name;
    private Double weight;
    private Double price;
    private Long reducerId;
    private ReducerDto reducer;
    private Long motorId;
    private MotorDto motor;
   // private Set<ProductOptionDto> options;
    private Set<Long> optionsIds;
    private Boolean imageEmpty;
    private String imageString;
    private Boolean imageChanged;
}
