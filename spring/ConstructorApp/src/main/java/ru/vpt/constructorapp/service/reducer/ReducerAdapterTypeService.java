package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.adapter.dto.ReducerAdapterTypeDto;

import java.util.List;

public interface ReducerAdapterTypeService {
    List<ReducerAdapterTypeDto> getAllReducerAdapterTypes();
    ReducerAdapterTypeDto getReducerAdapterById(Long id);
    List<ReducerAdapterTypeDto> getAllReducerAdapterTypesByReducerTypeId(Long id);
}
