package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;

import java.util.List;

public interface ReducerInputTypeService {
    List<ReducerInputTypeDto> getAllReducerInputTypes();
    ReducerInputTypeDto getReducerInputById(Long id);
    List<ReducerInputTypeDto> getAllReducerInputTypesByReducerTypeId(Long id);
}
