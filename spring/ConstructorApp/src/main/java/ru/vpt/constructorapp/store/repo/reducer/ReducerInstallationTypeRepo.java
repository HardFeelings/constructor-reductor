package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerInstallationTypeRepo extends CrudRepository<ReducerInstallationTypeEntity, Long> {
    List<ReducerInstallationTypeEntity> findAll();
    Optional<ReducerInstallationTypeEntity> findById(Long id);
    List<ReducerInstallationTypeEntity> findReducerInstallationTypeEntitiesByReducerType_IdReducerType(Long id);
}
