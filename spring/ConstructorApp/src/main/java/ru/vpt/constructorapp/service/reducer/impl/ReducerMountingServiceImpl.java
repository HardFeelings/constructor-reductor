package ru.vpt.constructorapp.service.reducer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.reducer.mounting.dto.ReducerMountingDto;
import ru.vpt.constructorapp.api.reducer.mounting.mapper.ReducerMountingMapper;
import ru.vpt.constructorapp.service.reducer.ReducerMountingService;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;
import ru.vpt.constructorapp.store.repo.reducer.ReducerMountingRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReducerMountingServiceImpl implements ReducerMountingService {

    private final ReducerMountingMapper reducerMountingMapper;
    private final ReducerMountingRepo reducerMountingRepo;

    @Override
    public List<ReducerMountingDto> getAllReducerMounting() {
        List<ReducerMountingEntity> entities = reducerMountingRepo.findAll();
        List<ReducerMountingDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(reducerMountingMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ReducerMountingDto getReducerMountingById(Long id) {
        ReducerMountingEntity entity = reducerMountingRepo.findById(id).get();
        return reducerMountingMapper.toDTO(entity);
    }

    @Override
    public ReducerMountingDto saveReducerMounting(ReducerMountingDto dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Невозможно сохранить крепление редуктора: dto равен null");
        }
        ReducerMountingEntity entity = reducerMountingMapper.toEntity(dto);
        return reducerMountingMapper.toDTO(reducerMountingRepo.save(entity));
    }

    @Override
    public Boolean deleteReducerMounting(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить крепление редуктора: id равен null");
        }
        if (!reducerMountingRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить крепление редуктора: не найден объект с id: " + id);
        }
        reducerMountingRepo.deleteById(id);
        return true;
    }

    @Override
    public ReducerMountingEntity findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно получить крепление редуктора: id равен null");
        }
        return reducerMountingRepo.findById(id).orElse(null);
    }
}
