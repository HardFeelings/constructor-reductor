package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.commercial.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {
    Optional<Role> findByRole(String role);
}
