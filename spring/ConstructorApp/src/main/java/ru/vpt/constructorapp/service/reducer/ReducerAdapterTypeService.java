package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerAdapterTypeEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerAdapterTypeService {
    List<ReducerAdapterTypeDto> getAllReducerAdapterTypes();

    ReducerAdapterTypeDto getReducerAdapterById(Long id);

    List<ReducerAdapterTypeDto> getAllReducerAdapterTypesByReducerTypeId(Long id);

    Boolean deleteReducerAdapterType(Long id);

    ReducerAdapterTypeDto saveReducerAdapterType(ReducerAdapterTypeDto reducerAdapterTypeDto);

    Optional<ReducerAdapterTypeEntity> findById(Long id);
}
