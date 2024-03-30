package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerSizeRepo extends CrudRepository<ReducerSizeEntity, Long> {
    List<ReducerSizeEntity> findAll();
    Optional<ReducerSizeEntity> findById(Long id);
    List<ReducerSizeEntity> findReducerSizeEntitiesByReducerType_IdReducerType(Long id);
}
