package ru.vpt.constructorapp.api.email.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EMailDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String productName;
    private String info;
}
