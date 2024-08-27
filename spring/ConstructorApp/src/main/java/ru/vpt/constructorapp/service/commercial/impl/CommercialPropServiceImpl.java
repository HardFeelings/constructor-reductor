package ru.vpt.constructorapp.service.commercial.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.commercial.prop.mapper.CommercialPropMapper;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.service.commercial.CommercialPropItemService;
import ru.vpt.constructorapp.service.commercial.CommercialPropService;
import ru.vpt.constructorapp.service.commercial.CommercialPropTermsService;
import ru.vpt.constructorapp.service.commercial.ReportService;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.repo.commercial.CommercialPropRepo;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommercialPropServiceImpl implements CommercialPropService {
    private final CommercialPropRepo repo;
    private final CommercialPropMapper mapper;
    private final CommercialPropItemService itemService;
    private final CommercialPropTermsService termsService;

    private final ReportService reportService;

    @Override
    public List<CommercialPropDto> getAll() {
        return repo.findAll().stream().
                map(mapper::toDTOWithoutItems).sorted(Comparator.comparingLong(CommercialPropDto::getIdCommercialProp))
                .collect(Collectors.toList());
    }

    @Override
    public CommercialPropDto getById(Long id) {
        CommercialPropEntity entity = repo.findById(id).orElseThrow(() -> new NotFoundException("commercialProp with id = " + id + " not found", 404));
        return mapper.toDTO(entity);
    }

    @Override
    public CommercialPropEntity findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("commercialProp with id = " + id + " not found", 404));
    }

    @Override
    public CommercialPropDto save(CommercialPropDto dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException("Невозможно сохранить опции продукта: dto равен null", 400);
        }
        CommercialPropEntity entity = mapper.toEntity(dto);

        double cost = 0;
        for( CommercialPropItemDto item : dto.getCommercialPropItems()){
            if(item.getProduct() != null && item.getProduct().getPrice() != null)
                cost += item.getProduct().getPrice() * item.getAmount();
        }
        if(dto.getMarginRatio() != null)
            cost *= dto.getMarginRatio();
        entity.setCost(BigDecimal.valueOf(cost));

        entity.setTimestamp(String.valueOf(dto.getTimestamp() == null ?
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : dto.getTimestamp()));
        CommercialPropDto savedDto = mapper.toDTO(repo.save(entity));
        List<CommercialPropItemDto> commercialPropItemDtos = dto.getCommercialPropItems().stream()
                .map(item -> itemService.save(item, savedDto.getIdCommercialProp())).toList();

        List<CommercialPropTermsDto> commercialPropTermsDtos = dto.getCommercialPropTerms().stream()
                .map(item -> termsService.save(item, savedDto.getIdCommercialProp())).toList();

        savedDto.setCommercialPropTerms(commercialPropTermsDtos.stream()
                        .sorted(Comparator.comparingLong(CommercialPropTermsDto::getOrd)).collect(Collectors.toList()));
        savedDto.setCommercialPropItems(commercialPropItemDtos);
        return savedDto;
    }

    @Override
    public Boolean delete(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить объект: id равен null", 400);
        }
        if (!repo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить объект: не найден объект с id: " + id, 404);
        }
        List<CommercialPropItemDto> list = itemService.getAllByCommercialPropId(id);
        list.forEach(item -> itemService.delete(item.getIdCommercialPropItem()));

        List<CommercialPropTermsDto> termsList = termsService.getAllByCommercialPropId(id);
        termsList.forEach(item -> termsService.delete(item.getIdCommercialPropTerms()));

        repo.deleteById(id);
        return true;
    }

    @Override
    public BufferedInputStream report(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно сформировать файл: id равен null", 400);
        }
        if (!repo.existsById(id)) {
            throw new NotFoundException("Невозможно сформировать файл: не найден объект с id: " + id, 404);
        }
        BufferedInputStream bufferedInputStream =  new BufferedInputStream(reportService.report(findById(id)));
        return bufferedInputStream;
    }

    @Override
    public List<CommercialPropDto> getByFilter(CommercialPropDto commercialPropDto) {
        return repo.findByFilter(commercialPropDto).stream().map(mapper::toDTOWithoutItems).collect(Collectors.toList());
    }
}
