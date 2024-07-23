package ru.vpt.constructorapp.service.reducer;

import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

import java.util.List;
import java.util.Optional;

public interface ReducerService {
    List<ReducerDto> getAllReducer();

    ReducerDto getReducerById(Long id);

    ReducerDto saveReducer(ReducerDto reducerDto);

    ReducerEntity saveReducerEntity(ReducerDto dto);

    Boolean deleteReducer(Long id);

    List<ReducerEntity> findByFilter(ReducerDto reducerDto);
    Optional<ReducerEntity> findById(Long id);
}
