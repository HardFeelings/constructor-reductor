package ru.vpt.constructorapp.api.reducer.type.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReducerTypeDto {
    private Long idReducerType;
    private String reducerTypeName;
}
