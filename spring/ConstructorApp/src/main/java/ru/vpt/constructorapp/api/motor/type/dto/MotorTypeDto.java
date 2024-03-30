package ru.vpt.constructorapp.api.motor.type.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MotorTypeDto {
    private Long idMotorType;
    private String motorTypeName;
}
