package ru.vpt.constructorapp.api.reducer.output.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReducerOutputShaftTypeDto {
    private Long idReducerOutputShaftType;
    private String reducerOutputShaftTypeValue;
    private Long reducerTypeId;
}
