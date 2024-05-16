package ru.vpt.constructorapp.api.motor.adapter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MotorAdapterTypeDto {

    private Long idMotorAdapterType;
    private String motorAdapterTypeValue;
    private Long motorTypeId;
}
