package ru.vpt.constructorapp.api.motor.adapter.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MotorAdapterTypeDto {

    private Long idMotorAdapterType;
    private String motorAdapterTypeValue;
    private Long motorTypeId;
}
