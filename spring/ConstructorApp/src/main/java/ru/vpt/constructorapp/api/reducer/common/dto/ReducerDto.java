package ru.vpt.constructorapp.api.reducer.common.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerDto {
    private Long idReducer;
    private Long reducerTypeId;
    private Long reducerSizeId;
    private Long reducerInputTypeId;
    private Long reducerOutputShaftTypeId;
    private Long reducerInstallationTypeId;
    private Long reducerMountingId;
    private Integer diameterOutputShaft;
    private Double ratio;
}
