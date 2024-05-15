package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;

import java.util.List;

public interface ReducerOutputShaftTypeService {
    List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypes();

    ReducerOutputShaftTypeDto getReducerOutputShaftById(Long id);

    List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypesByReducerTypeId(Long id);

    ReducerOutputShaftTypeDto saveReducerOutputShaftTypes(ReducerOutputShaftTypeDto reducerOutputShaftTypeDto);

    Boolean deleteReducerOutputShaftTypes(Long id);

    ReducerOutputShaftTypeEntity findById(Long id);
}
