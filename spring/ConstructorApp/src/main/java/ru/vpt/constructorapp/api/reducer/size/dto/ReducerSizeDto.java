package ru.vpt.constructorapp.api.reducer.size.dto;


import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerSizeDto {
    private Long idReducerSize;
    private String reducerSizeValue;
    private Long reducerTypeId;
}
