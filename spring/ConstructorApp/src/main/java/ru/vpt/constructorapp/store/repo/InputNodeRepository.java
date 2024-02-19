package ru.vpt.constructorapp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.InputNodeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface InputNodeRepository extends CrudRepository<InputNodeEntity,Long> {
    List<InputNodeEntity> findAll();
    Optional<InputNodeEntity> findById(Long id);
}
