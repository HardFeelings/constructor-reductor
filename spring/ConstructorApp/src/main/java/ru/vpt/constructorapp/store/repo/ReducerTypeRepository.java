package ru.vpt.constructorapp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vpt.constructorapp.store.entities.ReducerTypeEntity;

public interface ReducerTypeRepository extends JpaRepository<ReducerTypeEntity,Long> {
}
