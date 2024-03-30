package ru.vpt.constructorapp.api.motor.common.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.motor.adapter.dto.MotorAdapterTypeDto;
import ru.vpt.constructorapp.api.motor.type.dto.MotorTypeDto;


@Data
@Builder
public class MotorDto {
    private Long idMotor;
    private Double power;
    private Double frequency;
    private Double rpm;
    private MotorTypeDto motorType;
    private MotorAdapterTypeDto motorAdapterType;
}
