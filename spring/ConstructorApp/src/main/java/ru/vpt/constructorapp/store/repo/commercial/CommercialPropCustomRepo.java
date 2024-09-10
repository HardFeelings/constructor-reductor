package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

public interface CommercialPropCustomRepo {

    Page<CommercialPropEntity> findByFilter(CommercialPropDto filter, Pageable pageable);
}
