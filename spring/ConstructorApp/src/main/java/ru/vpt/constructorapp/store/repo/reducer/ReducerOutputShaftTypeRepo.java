package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerOutputShaftTypeRepo extends CrudRepository<ReducerOutputShaftTypeEntity,Long> {
    List<ReducerOutputShaftTypeEntity> findAll();
    Optional<ReducerOutputShaftTypeEntity> findById(Long id);
    List<ReducerOutputShaftTypeEntity> findReducerOutputShaftTypeEntitiesByReducerType_IdReducerType(Long id);
}
