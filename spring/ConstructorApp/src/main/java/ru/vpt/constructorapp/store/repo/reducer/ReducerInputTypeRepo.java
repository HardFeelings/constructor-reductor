package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerInputTypeRepo extends JpaRepository<ReducerInputTypeEntity, Long> {
    List<ReducerInputTypeEntity> findAll();
    Optional<ReducerInputTypeEntity> findById(Long id);
    List<ReducerInputTypeEntity> findReducerInputTypeEntitiesByReducerType_IdReducerType(Long id);
}
