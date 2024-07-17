package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommercialPropRepo extends CrudRepository<CommercialPropEntity, Long> {
    List<CommercialPropEntity> findAll();
    Optional<CommercialPropEntity> findById(Long id);
}
