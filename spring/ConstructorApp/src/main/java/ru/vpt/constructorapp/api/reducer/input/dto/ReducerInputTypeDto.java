package ru.vpt.constructorapp.api.reducer.input.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerInputTypeDto {
    private Long idReducerInputType;
    private String reducerInputTypeValue;
    private Long reducerTypeId;
}
