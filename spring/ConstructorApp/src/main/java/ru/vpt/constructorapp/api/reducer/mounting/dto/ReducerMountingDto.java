package ru.vpt.constructorapp.api.reducer.mounting.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerMountingDto {
    private Long idReducerMounting;

    private String reducerMountingValue;
}
