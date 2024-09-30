package ru.vpt.constructorapp.service.commercial.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.api.commercial.manager.dto.ManagerDto;
import ru.vpt.constructorapp.api.commercial.payment.dto.CommercialPropTermsDto;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropDto;
import ru.vpt.constructorapp.api.commercial.prop.dto.CommercialPropPaginationDto;
import ru.vpt.constructorapp.api.commercial.prop.mapper.CommercialPropMapper;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.service.commercial.*;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;
import ru.vpt.constructorapp.store.repo.commercial.CommercialPropRepo;
import ru.vpt.constructorapp.util.JwtUtils;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private final JwtUtils jwtUtils;
    private final ManagerService managerService;

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
            throw new BadRequestException("Невозможно сохранить коммерческое предложение: dto равен null", 400);
        }
        if (dto.getCommercialPropTerms() != null &&
                dto.getCommercialPropTerms().stream().mapToDouble(CommercialPropTermsDto::getPercent).sum() != 100)
            throw new BadRequestException("Невозможно сохранить коммерческое предложение: сумма процентов не равно 100", 400);

        CommercialPropEntity entity = mapper.toEntity(dto);

        double cost = 0;
        for (CommercialPropItemDto item : dto.getCommercialPropItems()) {
            if (item.getProduct() != null && item.getProduct().getPrice() != null)
                cost += item.getProduct().getPrice() * item.getAmount();
        }
        entity.setCost(BigDecimal.valueOf(cost));

        entity.setTimestamp(String.valueOf(dto.getTimestamp() == null ?
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : dto.getTimestamp()));
        CommercialPropDto savedDto = mapper.toDTO(repo.save(entity));
        if (!Objects.isNull(dto.getCommercialPropItems())) {
            List<CommercialPropItemDto> commercialPropItemDtos = dto.getCommercialPropItems().stream()
                    .map(item -> itemService.save(item, savedDto.getIdCommercialProp())).toList();
            savedDto.setCommercialPropItems(commercialPropItemDtos);
        }
        if (!Objects.isNull(dto.getCommercialPropTerms())) {

            List<CommercialPropTermsDto> commercialPropTermsDtos = dto.getCommercialPropTerms().stream()
                    .map(item -> termsService.save(item, savedDto.getIdCommercialProp())).toList();
            savedDto.setCommercialPropTerms(commercialPropTermsDtos.stream()
                    .sorted(Comparator.comparingLong(CommercialPropTermsDto::getOrd)).collect(Collectors.toList()));
        }
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
        BufferedInputStream bufferedInputStream = new BufferedInputStream(reportService.report(findById(id)));
        return bufferedInputStream;
    }

    @Override
    public CommercialPropPaginationDto getByFilter(String token, CommercialPropDto commercialPropDto, int offset, int limit) {
        List<String> roles = jwtUtils.getRoles(token.substring(7));
        for (String role : roles) {
            if (role.contains("ADMIN"))
                return findByFilter(commercialPropDto, offset, limit);
        }
        String user = jwtUtils.getUsername(token.substring(7));
        ManagerEntity managerEntity = managerService.getByUsername(user);
        if (!Objects.isNull(commercialPropDto)) {
            commercialPropDto.setManager(managerEntity);
        }
        return findByFilter(commercialPropDto, offset, limit);


    }

    private CommercialPropPaginationDto findByFilter(CommercialPropDto commercialPropDto, int offset, int limit) {
        Page<CommercialPropEntity> page = repo.findByFilter(commercialPropDto, PageRequest.of(offset, limit));
        CommercialPropPaginationDto paginationDto = new CommercialPropPaginationDto();
        paginationDto.setContent(page.getContent().stream().map(mapper::toDTOWithoutItems).collect(Collectors.toList()));
        paginationDto.setCurrentPage(offset);
        paginationDto.setTotalPages(page.getTotalPages());
        paginationDto.setTotalCount(page.getTotalElements());
        return paginationDto;
    }
}
