package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepo extends JpaRepository<ManagerEntity, Long> {
    List<ManagerEntity> findAll();
    Optional<ManagerEntity> findById(Long id);
}
