package ru.vpt.constructorapp.service.commercial;

import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> getAll();
    ManagerDto getById(Long id);
    ManagerEntity findById(Long id);
    ManagerDto save(ManagerDto managerDto);
    Boolean delete(Long id);
}
