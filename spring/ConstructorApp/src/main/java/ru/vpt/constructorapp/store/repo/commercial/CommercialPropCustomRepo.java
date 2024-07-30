package ru.vpt.constructorapp.store.repo.commercial;

import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.util.List;

public interface CommercialPropCustomRepo {

    List<CommercialPropEntity> findByFilter(CommercialPropDto filter);
}
