package ru.vpt.constructorapp.api.reducer.installation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReducerInstallationTypeDto {
    private Long idReducerInstallationType;
    private String reducerInstallationTypeValue;
    private Long reducerTypeId;
}
