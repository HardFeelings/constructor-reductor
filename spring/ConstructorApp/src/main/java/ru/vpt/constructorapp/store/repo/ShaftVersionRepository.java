package ru.vpt.constructorapp.store.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.ShaftVersionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShaftVersionRepository extends CrudRepository<ShaftVersionEntity,Long> {
    List<ShaftVersionEntity> findAll();
    Optional<ShaftVersionEntity> findById(Long id);
}
