package ru.vpt.constructorapp.api.reducer.size.dto;


import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;

@Data
@Builder
public class ReducerSizeDto {
    private Long idReducerSize;
    private String reducerSizeValue;
    private ReducerTypeDto reducerType;
}
