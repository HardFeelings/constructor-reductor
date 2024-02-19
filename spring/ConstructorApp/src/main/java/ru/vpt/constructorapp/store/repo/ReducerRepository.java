package ru.vpt.constructorapp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vpt.constructorapp.store.entities.ReducerEntity;

public interface ReducerRepository extends JpaRepository<ReducerEntity,Long> {
}
