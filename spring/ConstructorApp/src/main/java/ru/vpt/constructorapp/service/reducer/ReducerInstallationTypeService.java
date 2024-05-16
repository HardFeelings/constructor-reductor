package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.installation.dto.ReducerInstallationTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerInstallationTypeService {
    List<ReducerInstallationTypeDto> getAllReducerInstallationTypes();

    ReducerInstallationTypeDto getReducerInstallationById(Long id);

    List<ReducerInstallationTypeDto> getAllReducerInstallationTypesByReducerTypeId(Long id);

    ReducerInstallationTypeDto saveReducerInstallationType(ReducerInstallationTypeDto reducerInstallationTypeDto);

    Boolean deleteReducerInstallationType(Long id);

    Optional<ReducerInstallationTypeEntity> findById(Long id);
}
