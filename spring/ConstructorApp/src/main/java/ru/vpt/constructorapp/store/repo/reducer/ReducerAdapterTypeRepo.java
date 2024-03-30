package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerAdapterTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerAdapterTypeRepo extends CrudRepository<ReducerAdapterTypeEntity, Long> {
    List<ReducerAdapterTypeEntity> findAll();
    Optional<ReducerAdapterTypeEntity> findById(Long id);
    List<ReducerAdapterTypeEntity> findReducerAdapterTypeEntitiesByReducerType_IdReducerType(Long id);
}
