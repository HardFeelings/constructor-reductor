package ru.vpt.constructorapp.api.reducer.adapter.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;

@Data
@Builder
public class ReducerAdapterTypeDto {
    private Long idReducerAdapterType;
    private String reducerAdapterTypeValue;
    private ReducerTypeDto reducerType;
}
