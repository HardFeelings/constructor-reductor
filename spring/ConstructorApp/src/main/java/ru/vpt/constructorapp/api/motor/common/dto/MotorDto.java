package ru.vpt.constructorapp.api.motor.common.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MotorDto {
    private Long idMotor;
    private Double power;
    private Double frequency;
    private Double rpm;
    private Long motorTypeId;
    private Long motorAdapterTypeId;
}
