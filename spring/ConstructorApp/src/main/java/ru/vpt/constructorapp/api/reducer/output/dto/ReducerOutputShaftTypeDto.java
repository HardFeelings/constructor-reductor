package ru.vpt.constructorapp.api.reducer.output.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerOutputShaftTypeDto {
    private Long idReducerOutputShaftType;
    private String reducerOutputShaftTypeValue;
    private Long reducerTypeId;
}
