package ru.vpt.constructorapp.service.commercial;

import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerPaginationDto;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

import java.security.Principal;
import java.util.List;

public interface ManagerService {
    ManagerPaginationDto getAll(int offset, int limit);
    ManagerDto getById(Long id);
    ManagerEntity findById(Long id);
    ManagerDto save(ManagerDto managerDto);
    Boolean delete(Long id);
    List<ManagerDto> getAllWithoutPagination(String token);
    ManagerEntity getByUsername(String username);

}
