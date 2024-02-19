package ru.vpt.constructorapp.api.reducer.common.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.gearbox.version.dto.GearboxVersionDto;
import ru.vpt.constructorapp.api.shaft.version.dto.ShaftVersionDto;

@Data
@Builder
public class ReducerDto {
    private Long idReducer;
    private Double gearRatio;
    private GearboxVersionDto gearboxVersion;
    private ShaftVersionDto shaftVersion;
    private String imagePath;
}
