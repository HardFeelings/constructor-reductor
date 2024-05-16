package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerOutputShaftTypeService {
    List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypes();

    ReducerOutputShaftTypeDto getReducerOutputShaftById(Long id);

    List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypesByReducerTypeId(Long id);

    ReducerOutputShaftTypeDto saveReducerOutputShaftTypes(ReducerOutputShaftTypeDto reducerOutputShaftTypeDto);

    Boolean deleteReducerOutputShaftTypes(Long id);

    Optional<ReducerOutputShaftTypeEntity> findById(Long id);
}
