package ru.vpt.constructorapp.api.reducer.mounting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReducerMountingDto {
    private Long idReducerMounting;

    private String reducerMountingValue;
}
