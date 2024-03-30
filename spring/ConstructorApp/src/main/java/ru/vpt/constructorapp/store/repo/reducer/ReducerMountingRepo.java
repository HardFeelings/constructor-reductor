package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInstallationTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerMountingRepo extends CrudRepository<ReducerMountingEntity,Long> {
    List<ReducerMountingEntity> findAll();
    Optional<ReducerMountingEntity> findById(Long id);
}
