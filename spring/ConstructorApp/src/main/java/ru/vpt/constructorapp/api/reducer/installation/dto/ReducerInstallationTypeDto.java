package ru.vpt.constructorapp.api.reducer.installation.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerInstallationTypeDto {
    private Long idReducerInstallationType;
    private String reducerInstallationTypeValue;
    private Long reducerTypeId;
}
