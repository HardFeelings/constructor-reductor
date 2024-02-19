package ru.vpt.constructorapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.gearbox.version.dto.GearboxVersionDto;
import ru.vpt.constructorapp.api.gearbox.version.mapper.GearboxVersionMapper;
import ru.vpt.constructorapp.service.GearboxVersionService;
import ru.vpt.constructorapp.store.entities.GearboxVersionEntity;
import ru.vpt.constructorapp.store.repo.GearboxVersionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GearboxVersionServiceImpl implements GearboxVersionService {
    private final GearboxVersionRepository gearboxVersionRepository;
    private final GearboxVersionMapper gearboxVersionMapper;
    @Override
    public List<GearboxVersionDto> getAllGearboxVersion() {
        List<GearboxVersionDto> gearboxVersionDtos = new ArrayList<>();
        gearboxVersionRepository.findAll().forEach(item -> {
            gearboxVersionDtos.add(gearboxVersionMapper.toDTO(item));
        });
        return gearboxVersionDtos;
    }

    @Override
    public GearboxVersionDto getById(Long id) {
        GearboxVersionEntity entity = gearboxVersionRepository.findById(id).get();
        if(entity == null)
            return null;
        return gearboxVersionMapper.toDTO(entity);
    }
}
