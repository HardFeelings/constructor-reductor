package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;

import java.util.List;

public interface ReducerSizeService {
    List<ReducerSizeDto> getAllReducerSizes();
    ReducerSizeDto getReducerSizeById(Long id);
    List<ReducerSizeDto> getAllReducerSizesByReducerTypeId(Long id);
}
