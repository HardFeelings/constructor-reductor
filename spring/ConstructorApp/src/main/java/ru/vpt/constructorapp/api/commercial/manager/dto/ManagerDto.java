package ru.vpt.constructorapp.api.commercial.manager.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ManagerDto {
    private Long idManager;
    private String shortName;
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
}
