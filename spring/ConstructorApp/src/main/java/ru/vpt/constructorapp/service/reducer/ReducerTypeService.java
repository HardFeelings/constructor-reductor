package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

import java.util.List;

public interface ReducerTypeService {
    List<ReducerTypeDto> getAllReducerTypes();

    ReducerTypeDto getReducerTypeById(Long id);

    ReducerTypeDto saveReducerType(ReducerTypeDto reducerTypeDto);

    Boolean deleteReducerType(Long id);

    ReducerTypeEntity findById(Long id);
}
