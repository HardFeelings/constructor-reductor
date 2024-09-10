package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputPaginationDto;
import ru.vpt.constructorapp.api.reducer.input.dto.ReducerInputTypeDto;
import ru.vpt.constructorapp.api.reducer.input.mapper.ReducerInputTypeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerInputTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerInputTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerInputTypeRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReducerInputTypeServiceImpl implements ReducerInputTypeService {

    private final ReducerInputTypeMapper reducerInputTypeMapper;
    private final ReducerInputTypeRepo reducerInputTypeRepo;
    private final ReducerTypeServiceImpl reducerTypeService;

    @Override
    public ReducerInputPaginationDto getAllReducerInputTypes(int offset, int limit) {
        Page<ReducerInputTypeEntity> page = reducerInputTypeRepo.findAll(PageRequest.of(offset, limit, Sort.by("idReducerInputType")));
        List<ReducerInputTypeDto> dtos = new ArrayList<>();
        page.getContent().forEach(item -> dtos.add(reducerInputTypeMapper.toDTO(item)));
        ReducerInputPaginationDto paginationDto = new ReducerInputPaginationDto();
        paginationDto.setTotalCount(page.getTotalElements());
        paginationDto.setTotalPages(page.getTotalPages());
        paginationDto.setContent(dtos);
        paginationDto.setCurrentPage(offset);
        return paginationDto;
    }

    @Override
    public ReducerInputTypeDto getReducerInputById(Long id) {
        ReducerInputTypeEntity entity = reducerInputTypeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("reducerInputType with id = " + id + " not found", 404));
        return reducerInputTypeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerInputTypeDto> getAllReducerInputTypesByReducerTypeId(Long id) {
        List<ReducerInputTypeEntity> entities = reducerInputTypeRepo.findReducerInputTypeEntitiesByReducerType_IdReducerType(id);
        List<ReducerInputTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerInputTypeMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ReducerInputTypeDto::getIdReducerInputType)).collect(Collectors.toList());
    }

    @Override
    public ReducerInputTypeDto saveReducerInputType(ReducerInputTypeDto dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException("Невозможно сохранить тип входа редуктора: dto равен null", 400);
        }
        ReducerTypeEntity reducerTypeEntity = reducerTypeService.findById(dto.getReducerTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить тип входа редуктора: не найден тип редуктора с id: " + dto.getReducerTypeId(), 404));
        ReducerInputTypeEntity entity = reducerInputTypeMapper.toEntity(dto);
        entity.setReducerType(reducerTypeEntity);
        return reducerInputTypeMapper.toDTO(reducerInputTypeRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerInputType(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить тип входа редуктора: id равен null", 400);
        }
        if (!reducerInputTypeRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить тип входа редуктора: не найден объект с id: " + id, 404);
        }
        reducerInputTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<ReducerInputTypeEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно получить тип входа редуктора: id равен null", 400);
        }
        return reducerInputTypeRepo.findById(id);
    }
}
