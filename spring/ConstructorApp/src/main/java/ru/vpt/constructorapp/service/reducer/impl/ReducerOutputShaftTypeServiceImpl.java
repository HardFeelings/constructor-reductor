package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftPaginationDto;
import ru.vpt.constructorapp.api.reducer.output.dto.ReducerOutputShaftTypeDto;
import ru.vpt.constructorapp.api.reducer.output.mapper.ReducerOutputShaftTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerOutputShaftTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerOutputShaftTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerOutputShaftTypeRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReducerOutputShaftTypeServiceImpl implements ReducerOutputShaftTypeService {

    private final ReducerOutputShaftTypeMapper reducerOutputShaftTypeMapper;
    private final ReducerOutputShaftTypeRepo reducerOutputShaftTypeRepo;
    private final ReducerTypeServiceImpl reducerTypeService;

    @Override
    public ReducerOutputShaftPaginationDto getAllReducerOutputShaftTypes(int offset, int limit) {
        Page<ReducerOutputShaftTypeEntity> page = reducerOutputShaftTypeRepo
                .findAll(PageRequest.of(offset, limit, Sort.by("idReducerOutputShaftType")));
        List<ReducerOutputShaftTypeDto> dtos = new ArrayList<>();
        page.getContent().forEach(item -> dtos.add(reducerOutputShaftTypeMapper.toDTO(item)));
        ReducerOutputShaftPaginationDto dto = new ReducerOutputShaftPaginationDto();
        dto.setContent(dtos);
        dto.setTotalCount(page.getTotalElements());
        dto.setCurrentPage(offset);
        dto.setTotalPages(page.getTotalPages());
        return dto;
    }

    @Override
    public ReducerOutputShaftTypeDto getReducerOutputShaftById(Long id) {
        ReducerOutputShaftTypeEntity entity = reducerOutputShaftTypeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("reducerOutputShaftType with id = " + id + " not found", 404));
        return reducerOutputShaftTypeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerOutputShaftTypeDto> getAllReducerOutputShaftTypesByReducerTypeId(Long id) {
        List<ReducerOutputShaftTypeEntity> entities = reducerOutputShaftTypeRepo.findReducerOutputShaftTypeEntitiesByReducerType_IdReducerType(id);
        List<ReducerOutputShaftTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerOutputShaftTypeMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ReducerOutputShaftTypeDto::getIdReducerOutputShaftType)).collect(Collectors.toList());
    }

    @Override
    public ReducerOutputShaftTypeDto saveReducerOutputShaftTypes(ReducerOutputShaftTypeDto dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException("Невозможно сохранить тип выходного вала: dto равен null", 400);
        }
        ReducerTypeEntity reducerTypeEntity = reducerTypeService.findById(dto.getReducerTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить тип выходного вала: не найден тип редуктора с id: " + dto.getReducerTypeId(), 404));
        ReducerOutputShaftTypeEntity entity = reducerOutputShaftTypeMapper.toEntity(dto);
        entity.setReducerType(reducerTypeEntity);
        return reducerOutputShaftTypeMapper.toDTO(reducerOutputShaftTypeRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerOutputShaftTypes(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить тип выходного вала: id равен null", 400);
        }
        if (!reducerOutputShaftTypeRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить тип выходного вала: не найден объект с id: " + id, 404);
        }
        reducerOutputShaftTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<ReducerOutputShaftTypeEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно получить тип выходного вала: id равен null", 400);
        }
        return reducerOutputShaftTypeRepo.findById(id);
    }
}
