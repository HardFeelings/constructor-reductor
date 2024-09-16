package ru.vpt.constructorapp.api.auth.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegistrationDto {
    private String username;
    private String password;
    private Boolean isAdmin;
}
