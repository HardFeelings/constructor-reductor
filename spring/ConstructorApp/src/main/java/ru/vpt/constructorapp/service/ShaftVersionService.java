package ru.vpt.constructorapp.service;

import ru.vpt.constructorapp.api.shaft.version.dto.ShaftVersionDto;

import java.util.List;

public interface ShaftVersionService {
    List<ShaftVersionDto> getAllShaftVersion();
    ShaftVersionDto getById(Long id);

    List<ShaftVersionDto> getByReducerTypeId(Long id);
}
