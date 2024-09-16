package ru.vpt.constructorapp.service.commercial;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.store.entities.commercial.Role;
import ru.vpt.constructorapp.store.repo.commercial.RoleRepo;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepo roleRepo;

    public Role findByName(String name) {
        return roleRepo.findByRole(name).orElseThrow(() -> new NotFoundException("role with name = " + name + " not found", 404));
    }
}
