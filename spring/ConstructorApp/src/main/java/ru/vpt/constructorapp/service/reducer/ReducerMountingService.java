package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;

import java.util.List;

public interface ReducerMountingService {
    List<ReducerMountingDto> getAllReducerMounting();
    ReducerMountingDto getReducerMountingById(Long id);
}
