package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;

import java.util.List;

public interface ReducerService {
    List<ReducerDto> getAllReducer();
    ReducerDto getReducerById(Long id);
}
