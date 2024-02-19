package ru.vpt.constructorapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.shaft.version.dto.ShaftVersionDto;
import ru.vpt.constructorapp.api.shaft.version.mapper.ShaftVersionMapper;
import ru.vpt.constructorapp.service.ShaftVersionService;
import ru.vpt.constructorapp.store.entities.ShaftVersionEntity;
import ru.vpt.constructorapp.store.repo.ShaftVersionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShaftVersionServiceImpl implements ShaftVersionService {
    private final ShaftVersionRepository shaftVersionRepository;
    private final ShaftVersionMapper shaftVersionMapper;

    @Override
    public List<ShaftVersionDto> getAllShaftVersion() {
        List<ShaftVersionDto> shaftVersionDtos = new ArrayList<>();
        shaftVersionRepository.findAll().forEach(item -> {
            shaftVersionDtos.add(shaftVersionMapper.toDTO(item));
        });
        return shaftVersionDtos;
    }

    @Override
    public ShaftVersionDto getById(Long id) {
        ShaftVersionEntity entity = shaftVersionRepository.findById(id).get();
        if(entity == null)
            return null;
        return shaftVersionMapper.toDTO(entity);
    }

    @Override
    public List<ShaftVersionDto> getByReducerTypeId(Long id) {
        List<ShaftVersionDto> shaftVersionDtos = new ArrayList<>();
        shaftVersionRepository.findShaftVersionEntitiesByReducerType_IdReducerType(id).forEach(item -> {
            shaftVersionDtos.add(shaftVersionMapper.toDTO(item));
        });
        return shaftVersionDtos;
    }
}
