package ru.vpt.constructorapp.service;

import ru.vpt.constructorapp.api.reducer.type.dto.ReducerTypeDto;

import java.util.List;

public interface ReducerTypeService {
    List<ReducerTypeDto> getAllReducerTypes();
    ReducerTypeDto getById(Long id);
}
