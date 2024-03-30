package ru.vpt.constructorapp.api.motor.adapter.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;

@Data
@Builder
public class MotorAdapterTypeDto {

    private Long idMotorAdapterType;
    private String motorAdapterTypeValue;
    private MotorTypeDto motorType;
}
