package ru.vpt.constructorapp.api.reducer.installation.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;

@Data
@Builder
public class ReducerInstallationTypeDto {
    private Long idReducerInstallationType;
    private String reducerInstallationTypeValue;
    private ReducerTypeDto reducerType;
}
