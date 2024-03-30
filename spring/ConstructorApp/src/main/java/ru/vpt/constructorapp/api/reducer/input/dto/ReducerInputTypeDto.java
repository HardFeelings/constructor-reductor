package ru.vpt.constructorapp.api.reducer.input.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;

@Data
@Builder
public class ReducerInputTypeDto {
    private Long idReducerInputType;
    private String reducerInputTypeValue;
    private ReducerTypeDto reducerType;
}
