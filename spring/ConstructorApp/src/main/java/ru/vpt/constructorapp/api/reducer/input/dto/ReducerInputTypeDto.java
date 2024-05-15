package ru.vpt.constructorapp.api.reducer.input.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReducerInputTypeDto {
    private Long idReducerInputType;
    private String reducerInputTypeValue;
    private Long reducerTypeId;
}
