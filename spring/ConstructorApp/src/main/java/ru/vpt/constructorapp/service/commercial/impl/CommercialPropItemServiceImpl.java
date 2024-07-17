package ru.vpt.constructorapp.service.commercial.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.commercial.item.mapper.CommercialPropItemMapper;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.service.commercial.CommercialPropItemService;
import ru.vpt.constructorapp.service.commercial.CommercialPropService;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.repo.commercial.CommercialPropItemRepo;
import ru.vpt.constructorapp.store.repo.commercial.CommercialPropRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommercialPropItemServiceImpl implements CommercialPropItemService {

    private final CommercialPropItemRepo repo;
    private final CommercialPropItemMapper mapper;
    private final CommercialPropRepo commercialPropRepo;

    @Override
    public List<CommercialPropItemDto> getAllByCommercialPropId(Long id) {
        if(Objects.isNull(id)){
            throw new BadRequestException("invalid request: id can not be null", 400);
        }
        return repo.findAllByCommercialProp_IdCommercialProp(id).stream().
                map(mapper::toDTO).sorted(Comparator.comparingLong(CommercialPropItemDto::getIdCommercialPropItem))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить объект: id равен null", 400);
        }
        if (!repo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить объект: не найден объект с id: " + id, 404);
        }
        repo.deleteById(id);
        return true;
    }

    @Override
    public CommercialPropItemDto save(CommercialPropItemDto commercialPropItemDto, Long id) {
        if (Objects.isNull(commercialPropItemDto)) {
            throw new BadRequestException("Невозможно сохранить элемент: dto равен null", 400);
        }
        CommercialPropEntity commercialProp = commercialPropRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("commercialProp with id = " + id + " not found", 404));
        CommercialPropItemEntity entity = mapper.toEntity(commercialPropItemDto);
        entity.setCommercialProp(commercialProp);
        return mapper.toDTO(repo.save(entity));
    }
}
