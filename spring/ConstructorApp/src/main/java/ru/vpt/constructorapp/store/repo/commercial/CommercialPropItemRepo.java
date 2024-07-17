package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;

import java.util.List;

@Repository
public interface CommercialPropItemRepo extends CrudRepository<CommercialPropItemEntity,Long> {
    List<CommercialPropItemEntity> findAllByCommercialProp_IdCommercialProp(Long id);
}
