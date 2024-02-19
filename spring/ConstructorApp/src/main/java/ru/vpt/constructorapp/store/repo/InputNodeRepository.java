package ru.vpt.constructorapp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vpt.constructorapp.store.entities.InputNodeEntity;

public interface InputNodeRepository extends JpaRepository<InputNodeEntity,Long> {
}
