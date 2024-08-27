package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.repository.CrudRepository;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropTermsEntity;

import java.util.List;

public interface CommercialPropTermsRepo extends CrudRepository<CommercialPropTermsEntity,Long> {
    List<CommercialPropTermsEntity> findAllByCommercialProp_IdCommercialProp(Long id);
}
