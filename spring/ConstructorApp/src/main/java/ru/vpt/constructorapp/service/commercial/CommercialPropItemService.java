package ru.vpt.constructorapp.service.commercial;

import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;

import java.util.List;

public interface CommercialPropItemService {
    List<CommercialPropItemDto> getAllByCommercialPropId(Long id);

    Boolean delete(Long id);

    CommercialPropItemDto save(CommercialPropItemDto commercialPropItemDto, Long id);
}
