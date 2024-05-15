package ru.vpt.constructorapp.api.reducer.size.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReducerSizeDto {
    private Long idReducerSize;
    private String reducerSizeValue;
    private Long reducerTypeId;
}
