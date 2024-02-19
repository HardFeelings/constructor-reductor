package ru.vpt.constructorapp.api.gearbox.version.dto;

import lombok.Builder;
import lombok.Data;
import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;

@Data
@Builder
public class GearboxVersionDto {
    private Long idGearboxVersion;
    private String gearboxVersionName;
    private ReducerTypeDto reducerType;
    private String imagePath;

}
