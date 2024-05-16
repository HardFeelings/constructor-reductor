package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerService {
    List<ReducerDto> getAllReducer();

    ReducerDto getReducerById(Long id);

    ReducerDto saveReducer(ReducerDto reducerDto);

    Boolean deleteReducer(Long id);

    Optional<ReducerEntity> findById(Long id);
}
