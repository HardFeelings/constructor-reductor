package ru.vpt.constructorapp.store.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.ReducerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerRepository extends CrudRepository<ReducerEntity,Long> {
    List<ReducerEntity> findAll();
    Optional<ReducerEntity> findById(Long id);
}
