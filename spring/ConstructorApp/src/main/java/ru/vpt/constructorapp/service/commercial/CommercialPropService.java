package ru.vpt.constructorapp.service.commercial;

import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropPaginationDto;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;

import java.io.BufferedInputStream;
import java.util.List;

public interface CommercialPropService {
    List<CommercialPropDto> getAll();
    CommercialPropDto getById(Long id);
    CommercialPropEntity findById(Long id);
    CommercialPropDto save(CommercialPropDto dto);
    Boolean delete(Long id);
    BufferedInputStream report(Long id);
    CommercialPropPaginationDto getByFilter(CommercialPropDto commercialPropDto, int offset, int limit);
}
