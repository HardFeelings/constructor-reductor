package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerDto;
import ru.vpt.constructorapp.api.reducer.common.dto.ReducerPaginationDto;
import ru.vpt.constructorapp.api.reducer.common.mapper.ReducerMapper;
import ru.vpt.constructorapp.service.reducer.ReducerService;
import ru.vpt.constructorapp.store.entities.reducer.*;
import ru.vpt.constructorapp.store.repo.reducer.ReducerRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReducerServiceImpl implements ReducerService {

    private final ReducerMapper reducerMapper;
    private final ReducerRepo reducerRepo;
    private final ReducerTypeServiceImpl reducerTypeServiceImpl;
    private final ReducerSizeServiceImpl reducerSizeServiceImpl;
    private final ReducerInputTypeServiceImpl reducerInputTypeServiceImpl;
    private final ReducerOutputShaftTypeServiceImpl reducerOutputShaftTypeServiceImpl;
    private final ReducerInstallationTypeServiceImpl reducerInstallationTypeServiceImpl;
    private final ReducerMountingServiceImpl reducerMountingServiceImpl;

    @Override
    public ReducerPaginationDto getAllReducer(int offset, int limit) {
        Page<ReducerEntity> page = reducerRepo.findAll(PageRequest.of(offset, limit, Sort.by("idReducer")));
        List<ReducerDto> dtos = new ArrayList<>();
        page.getContent().forEach(item -> dtos.add(reducerMapper.toDTO(item)));
        ReducerPaginationDto paginationDto = new ReducerPaginationDto();
        paginationDto.setContent(dtos);
        paginationDto.setCurrentPage(offset);
        paginationDto.setTotalPages(page.getTotalPages());
        paginationDto.setTotalCount(page.getTotalElements());
        return paginationDto;
    }

    @Override
    public ReducerDto getReducerById(Long id) {
        ReducerEntity entity = reducerRepo.findById(id).orElseThrow(() -> new NotFoundException("reducer with id = " + id + " not found", 404));
        return reducerMapper.toDTO(entity);
    }

    @Override
    public ReducerDto saveReducer(ReducerDto dto) {
        return reducerMapper.toDTO(saveReducerEntity(dto));
    }

    @Override
    public ReducerEntity saveReducerEntity(ReducerDto dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException("Невозможно сохранить редуктор: dto равен null", 400);
        }
        ReducerTypeEntity reducerType = reducerTypeServiceImpl.findById(dto.getReducerTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить редуктор: не найден тип редуктора с id: " + dto.getReducerTypeId(), 404));
        ReducerSizeEntity reducerSize = reducerSizeServiceImpl.findById(dto.getReducerSizeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить редуктор: не найден размер редуктора с id: " + dto.getReducerSizeId(), 404));
        ReducerInputTypeEntity reducerInputType = reducerInputTypeServiceImpl.findById(dto.getReducerInputTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить редуктор: не найден тип входа редуктора с id: " + dto.getReducerInputTypeId(), 404));
           ReducerOutputShaftTypeEntity reducerOutputShaftType = reducerOutputShaftTypeServiceImpl.findById(dto.getReducerOutputShaftTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить редуктор: не найден тип выходного вала с id: " + dto.getReducerOutputShaftTypeId(), 404));
        ReducerInstallationTypeEntity reducerInstallationType = reducerInstallationTypeServiceImpl.findById(dto.getReducerInstallationTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить редуктор: не найден тип установки редуктора с id: " + dto.getReducerInstallationTypeId(), 404));
        ReducerMountingEntity reducerMounting = reducerMountingServiceImpl.findById(dto.getReducerMountingId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить редуктор: не найдено крепление редуктора с id: " + dto.getReducerMountingId(), 404));
        ReducerEntity entity = reducerMapper.toEntity(dto);
        entity.setReducerType(reducerType);
        entity.setReducerSize(reducerSize);
        entity.setReducerInputType(reducerInputType);
        entity.setReducerOutputShaftType(reducerOutputShaftType);
        entity.setReducerInstallationType(reducerInstallationType);
        entity.setReducerMounting(reducerMounting);
        return reducerRepo.save(entity);
    }

    @Override
    public Boolean deleteReducer(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить редуктор: id равен null", 400);
        }
        if (!reducerRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить редуктор: не найден объект с id: " + id, 404);
        }
        reducerRepo.deleteById(id);
        return true;
    }

    @Override
    public List<ReducerEntity> findByFilter(ReducerDto reducerDto) {
        return reducerRepo.findByAllParameters(reducerDto.getDiameterOutputShaft(),
                reducerDto.getRatio(), reducerDto.getReducerInputTypeId(),
                reducerDto.getReducerInstallationTypeId(), reducerDto.getReducerMountingId(), reducerDto.getReducerOutputShaftTypeId(),
                reducerDto.getReducerSizeId(), reducerDto.getReducerTypeId());
    }

    @Override
    public Optional<ReducerEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно получить редуктор: id равен null", 400);
        }
        return reducerRepo.findById(id);
    }
}
