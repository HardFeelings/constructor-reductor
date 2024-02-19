package ru.vpt.constructorapp.service;

import ru.vpt.constructorapp.api.gearbox.version.dto.GearboxVersionDto;

import java.util.List;

public interface GearboxVersionService {
    List<GearboxVersionDto> getAllGearboxVersion();
    GearboxVersionDto getById(Long id);

    List<GearboxVersionDto> getByReducerTypeId(Long id);
}
