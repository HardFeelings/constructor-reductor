package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;

import java.util.List;

public interface ReducerOutputShaftTypeService {
    List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypes();
    ReducerOutputShaftTypeDto getReducerOutputShaftById(Long id);
    List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypesByReducerTypeId(Long id);
}
