package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;

import java.util.List;

public interface ReducerMountingService {
    List<ReducerMountingDto> getAllReducerMounting();
    ReducerMountingDto getReducerMountingById(Long id);
    ReducerMountingDto saveReducerMounting(ReducerMountingDto reducerMountingDto);
    Boolean deleteReducerMounting(Long id);
    ReducerMountingEntity findById(Long id);
}
