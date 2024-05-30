package ru.vpt.constructorapp.api.reducer.common.dto;

import lombok.*;
import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerDto {
    private Long idReducer;
    private ReducerTypeDto reducerType;
    private ReducerSizeDto reducerSize;
    private ReducerInputTypeDto reducerInputType;
    private ReducerAdapterTypeDto reducerAdapterType;
    private ReducerOutputShaftTypeDto reducerOutputShaftType;
    private ReducerInstallationTypeDto reducerInstallationType;
    private ReducerMountingDto reducerMounting;
    private Integer diameterInputShaft;
    private Integer diameterOutputShaft;
    private Double ratio;
    private Double torqueMoment;
}
