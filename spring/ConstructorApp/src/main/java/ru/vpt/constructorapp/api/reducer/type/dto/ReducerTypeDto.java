package ru.vpt.constructorapp.api.reducer.type.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerTypeDto {
    private Long idReducerType;
    private String reducerTypeName;
}
