package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;

import java.util.List;

public interface ReducerInputTypeService {
    List<ReducerInputTypeDto> getAllReducerInputTypes();

    ReducerInputTypeDto getReducerInputById(Long id);

    List<ReducerInputTypeDto> getAllReducerInputTypesByReducerTypeId(Long id);

    ReducerInputTypeDto saveReducerInputType(ReducerInputTypeDto reducerInputTypeDto);

    Boolean deleteReducerInputType(Long id);

    ReducerInputTypeEntity findById(Long id);
}
