package ru.vpt.constructorapp.api.reducer.output.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;

@Data
@Builder
public class ReducerOutputShaftTypeDto {
    private Long idReducerOutputShaftType;
    private String reducerOutputShaftTypeValue;
    private ReducerTypeDto reducerType;
}
