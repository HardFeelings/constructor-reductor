package ru.vpt.constructorapp.store.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.GearboxVersionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface GearboxVersionRepository extends CrudRepository<GearboxVersionEntity,Long> {
    List<GearboxVersionEntity> findAll();
    Optional<GearboxVersionEntity> findById(Long id);
}
