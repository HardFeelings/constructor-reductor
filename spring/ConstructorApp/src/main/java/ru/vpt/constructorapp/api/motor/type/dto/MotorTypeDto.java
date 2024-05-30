package ru.vpt.constructorapp.api.motor.type.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MotorTypeDto {
    private Long idMotorType;
    private String motorTypeName;
}
