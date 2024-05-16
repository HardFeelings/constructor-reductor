package ru.vpt.constructorapp.api.reducer.adapter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReducerAdapterTypeDto {
    private Long idReducerAdapterType;
    private String reducerAdapterTypeValue;
    private Long reducerTypeId;
}
