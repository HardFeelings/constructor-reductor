package ru.vpt.constructorapp.api.motor.common.dto;

import jakarta.persistence.Column;
import lombok.*;


@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MotorDto {
    private Long idMotor;
    private Double power;
    private Double frequency;
    private Double efficiency;
    private Double ratedCurrent;
    private Double posTerminalBox;
    private Double momentOfInertia;
    private String cableExitSide;
    private Long motorTypeId;
    private Long motorAdapterTypeId;
}
