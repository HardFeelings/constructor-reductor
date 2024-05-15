package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.size.dto.ReducerSizeDto;
import ru.vpt.constructorapp.api.reducer.size.mapper.ReducerSizeMapper;
import ru.vpt.constructorapp.service.reducer.ReducerSizeService;
import ru.vpt.constructorapp.service.reducer.ReducerTypeService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerSizeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerTypeEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerSizeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReducerSizeServiceImpl implements ReducerSizeService {

    private final ReducerSizeMapper reducerSizeMapper;
    private final ReducerSizeRepo reducerSizeRepo;
    private final ReducerTypeServiceImpl reducerTypeService;

    @Override
    public List<ReducerSizeDto> getAllReducerSizes() {
        List<ReducerSizeEntity> entities = reducerSizeRepo.findAll();
        List<ReducerSizeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerSizeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerSizeDto getReducerSizeById(Long id) {
        ReducerSizeEntity entity = reducerSizeRepo.findById(id).get();
        return reducerSizeMapper.toDTO(entity);
    }

    @Override
    public List<ReducerSizeDto> getAllReducerSizesByReducerTypeId(Long id) {
        List<ReducerSizeEntity> entities = reducerSizeRepo.findReducerSizeEntitiesByReducerType_IdReducerType(id);
        List<ReducerSizeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerSizeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerSizeDto saveReducerSize(ReducerSizeDto dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Невозможно сохранить размер редуктора: dto равен null");
        }
        ReducerTypeEntity reducerTypeEntity = reducerTypeService.findById(dto.getReducerTypeId());
        if (Objects.isNull(reducerTypeEntity)) {
            throw new RuntimeException("Невозможно сохранить размер редуктора: не найден тип редуктора с id: " + dto.getReducerTypeId());
        }
        ReducerSizeEntity entity = reducerSizeMapper.toEntity(dto);
        entity.setReducerType(reducerTypeEntity);
        return reducerSizeMapper.toDTO(reducerSizeRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerSize(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить размер редуктора: id равен null");
        }
        if (!reducerSizeRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить размер редуктора: не найден объект с id: " + id);
        }
        reducerSizeRepo.deleteById(id);
        return true;
    }

    @Override
    public ReducerSizeEntity findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно получить размер редуктора: id равен null");
        }
        return reducerSizeRepo.findById(id).orElse(null);
    }
}
