package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizePaginationDto;
import ru.vpt.constructorapp.api.reducer.size.mapper.ReducerSizeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerSizeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerSizeRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReducerSizeServiceImpl implements ReducerSizeService {

    private final ReducerSizeMapper reducerSizeMapper;
    private final ReducerSizeRepo reducerSizeRepo;
    private final ReducerTypeServiceImpl reducerTypeService;

    @Override
    public ReducerSizePaginationDto getAllReducerSizes(int offset, int limit) {
        Page<ReducerSizeEntity> page = reducerSizeRepo.findAll(PageRequest.of(offset, limit, Sort.by("idReducerSize")));
        List<ReducerSizeDto> dtos = new ArrayList<>();
        page.getContent().forEach(item -> dtos.add(reducerSizeMapper.toDTO(item)));
        ReducerSizePaginationDto paginationDto = new ReducerSizePaginationDto();
        paginationDto.setContent(dtos);
        paginationDto.setTotalCount(page.getTotalElements());
        paginationDto.setTotalPages(page.getTotalPages());
        paginationDto.setCurrentPage(page.getNumber());
        return paginationDto;
    }

    @Override
    public ReducerSizeDto getReducerSizeById(Long id) {
        ReducerSizeEntity entity = reducerSizeRepo.findById(id).orElseThrow(() -> new NotFoundException("reducerSize with id = " + id + " not found", 404));
        return reducerSizeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerSizeDto> getAllReducerSizesByReducerTypeId(Long id) {
        List<ReducerSizeEntity> entities = reducerSizeRepo.findReducerSizeEntitiesByReducerType_IdReducerType(id);
        List<ReducerSizeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerSizeMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ReducerSizeDto::getIdReducerSize)).collect(Collectors.toList());
    }

    @Override
    public ReducerSizeDto saveReducerSize(ReducerSizeDto dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException("Невозможно сохранить размер редуктора: dto равен null", 400);
        }
        ReducerTypeEntity reducerTypeEntity = reducerTypeService.findById(dto.getReducerTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить размер редуктора: не найден тип редуктора с id: " + dto.getReducerTypeId(), 404));
        ReducerSizeEntity entity = reducerSizeMapper.toEntity(dto);
        entity.setReducerType(reducerTypeEntity);
        return reducerSizeMapper.toDTO(reducerSizeRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerSize(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить размер редуктора: id равен null", 400);
        }
        if (!reducerSizeRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить размер редуктора: не найден объект с id: " + id, 404);
        }
        reducerSizeRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<ReducerSizeEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно получить размер редуктора: id равен null", 400);
        }
        return reducerSizeRepo.findById(id);
    }
}
