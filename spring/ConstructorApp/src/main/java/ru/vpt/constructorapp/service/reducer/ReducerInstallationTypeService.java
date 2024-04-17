package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;

import java.util.List;

public interface ReducerInstallationTypeService {
    List<ReducerInstallationTypeDto> getAllReducerInstallationTypes();
    ReducerInstallationTypeDto getReducerInstallationById(Long id);
    List<ReducerInstallationTypeDto> getAllReducerInstallationTypesByReducerTypeId(Long id);
}
