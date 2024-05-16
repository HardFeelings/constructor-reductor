package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerSizeService {
    List<ReducerSizeDto> getAllReducerSizes();

    ReducerSizeDto getReducerSizeById(Long id);

    List<ReducerSizeDto> getAllReducerSizesByReducerTypeId(Long id);

    ReducerSizeDto saveReducerSize(ReducerSizeDto reducerSizeDto);

    Boolean deleteReducerSize(Long id);

    Optional<ReducerSizeEntity> findById(Long id);
}
