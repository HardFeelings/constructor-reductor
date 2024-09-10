package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerPaginationDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerService {
    ReducerPaginationDto getAllReducer(int offset, int limit);

    ReducerDto getReducerById(Long id);

    ReducerDto saveReducer(ReducerDto reducerDto);

    ReducerEntity saveReducerEntity(ReducerDto dto);

    Boolean deleteReducer(Long id);

    List<ReducerEntity> findByFilter(ReducerDto reducerDto);
    Optional<ReducerEntity> findById(Long id);
}
