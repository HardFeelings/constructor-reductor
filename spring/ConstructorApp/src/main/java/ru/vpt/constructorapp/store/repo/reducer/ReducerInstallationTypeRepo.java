package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerInstallationTypeRepo extends JpaRepository<ReducerInstallationTypeEntity, Long> {
    List<ReducerInstallationTypeEntity> findAll();
    Optional<ReducerInstallationTypeEntity> findById(Long id);
    List<ReducerInstallationTypeEntity> findReducerInstallationTypeEntitiesByReducerType_IdReducerType(Long id);
}
