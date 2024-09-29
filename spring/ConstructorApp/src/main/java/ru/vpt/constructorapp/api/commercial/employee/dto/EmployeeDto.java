package ru.vpt.constructorapp.api.commercial.employee.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeeDto {
    private Long idEmployee;
    private String login;
    private String password;
    private Boolean admin;
}
