package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerTypeRepo extends CrudRepository<ReducerTypeEntity, Long> {
    List<ReducerTypeEntity> findAll();
    Optional<ReducerTypeEntity> findById(Long id);
}
