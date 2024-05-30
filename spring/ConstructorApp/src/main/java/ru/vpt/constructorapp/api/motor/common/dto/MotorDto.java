package ru.vpt.constructorapp.api.motor.common.dto;

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
    private Double rpm;
    private Long motorTypeId;
    private Long motorAdapterTypeId;
}
