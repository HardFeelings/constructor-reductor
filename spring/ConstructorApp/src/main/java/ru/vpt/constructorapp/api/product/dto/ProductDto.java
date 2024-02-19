package ru.vpt.constructorapp.api.product.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.input.node.dto.InputNodeDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;

@Data
@Builder
public class ProductDto {
    private Long idProduct;
    private String productName;
    private InputNodeDto inputNode;
    private ReducerDto reducer;
    private Double shaftDimension;
    private Double flangeDiameter;
    private Double outputRPM;
    private Double inputRPM;
    private Double radialLoad;
    private Double serviceFactor;
    private Double outputTorque;
    private Double axialLoad;
    private Double motorPower;
}
