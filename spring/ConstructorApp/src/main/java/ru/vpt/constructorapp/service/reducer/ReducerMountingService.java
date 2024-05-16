package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerMountingService {
    List<ReducerMountingDto> getAllReducerMounting();
    ReducerMountingDto getReducerMountingById(Long id);
    ReducerMountingDto saveReducerMounting(ReducerMountingDto reducerMountingDto);
    Boolean deleteReducerMounting(Long id);
    Optional<ReducerMountingEntity> findById(Long id);
}
