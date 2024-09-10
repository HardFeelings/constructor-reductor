package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputPaginationDto;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerInputTypeService {
    ReducerInputPaginationDto getAllReducerInputTypes(int offset, int limit);

    ReducerInputTypeDto getReducerInputById(Long id);

    List<ReducerInputTypeDto> getAllReducerInputTypesByReducerTypeId(Long id);

    ReducerInputTypeDto saveReducerInputType(ReducerInputTypeDto reducerInputTypeDto);

    Boolean deleteReducerInputType(Long id);

    Optional<ReducerInputTypeEntity> findById(Long id);
}
