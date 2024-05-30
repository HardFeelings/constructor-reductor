package ru.vpt.constructorapp.api.reducer.adapter.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerAdapterTypeDto {
    private Long idReducerAdapterType;
    private String reducerAdapterTypeValue;
    private Long reducerTypeId;
}
