package ru.vpt.constructorapp.api.shaft.version.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;


@Data
@Builder
public class ShaftVersionDto {
    private Long idShaftVersion;
    private String shaftVersionName;
    private ReducerTypeDto reducerType;
    private String imagePath;
}
