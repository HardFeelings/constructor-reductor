package ru.vpt.constructorapp.service.motor.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.motor.common.dto.MotorDto;
import ru.vpt.constructorapp.api.motor.common.dto.MotorPaginationDto;
import ru.vpt.constructorapp.api.motor.common.mapper.MotorMapper;
import ru.vpt.constructorapp.service.motor.MotorService;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;
import ru.vpt.constructorapp.store.repo.motor.MotorRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {
    private final MotorRepo motorRepo;
    private final MotorMapper motorMapper;
    private final MotorTypeServiceImpl motorTypeService;
    private final MotorAdapterTypeServiceImpl motorAdapterTypeService;

    @Override
    public MotorPaginationDto getAllMotors(int offset, int limit) {
        Page<MotorEntity> page = motorRepo.findAll(PageRequest.of(offset, limit, Sort.by("idMotor")));
        List<MotorDto> dtos = new ArrayList<>();
        page.getContent().forEach(item -> dtos.add(motorMapper.toDTO(item)));
        MotorPaginationDto paginationDto = new MotorPaginationDto();
        paginationDto.setMotorDtos(dtos);
        paginationDto.setTotalCount(page.getTotalElements());
        paginationDto.setCurrentPage(offset);
        paginationDto.setTotalPages(page.getTotalPages());
        return paginationDto;
    }

    @Override
    public MotorDto getMotorById(Long id) {
        MotorEntity entity = motorRepo.findById(id).orElseThrow(() -> new NotFoundException("motor with id = " + id + " not found", 404));
        return motorMapper.toDTO(entity);
    }

    @Override
    public MotorDto saveMotor(MotorDto motorDto) {
        return motorMapper.toDTO(saveMotorEntity(motorDto));
    }



    @Override
    public MotorEntity saveMotorEntity(MotorDto motorDto) {
        if (Objects.isNull(motorDto)) {
            throw new BadRequestException("Невозможно сохранить мотор: dto равен null", 400);
        }
        MotorTypeEntity motorTypeEntity = motorTypeService.findById(motorDto.getMotorTypeId());
        MotorEntity entity = motorMapper.toEntity(motorDto);
        if (Objects.isNull(motorTypeEntity)) {
            throw new NotFoundException("Невозможно сохранить мотор: не найден тип мотора", 404);
        }
        entity.setMotorAdapterType(null);
        if(motorDto.getMotorAdapterTypeId() != null){
            MotorAdapterTypeEntity motorAdapterTypeEntity = motorAdapterTypeService.findById(motorDto.getMotorAdapterTypeId());
            entity.setMotorAdapterType(motorAdapterTypeEntity);
        }
        entity.setMotorType(motorTypeEntity);
        return motorRepo.save(entity);
    }

    @Override
    public Boolean deleteMotor(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить объект: id равен null", 400);
        }
        if (!motorRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить объект: не найден объект с id: " + id, 404);
        }
        motorRepo.deleteById(id);
        return true;
    }

    @Override
    public List<MotorEntity> findByFilter(MotorDto motorDto) {
        return motorRepo.findByAllParameters(motorDto.getMotorAdapterTypeId(), motorDto.getMotorTypeId(),
                motorDto.getEfficiency(), motorDto.getFrequency(),
                motorDto.getMomentOfInertia(),
                motorDto.getPower(), motorDto.getRatedCurrent());
    }

    @Override
    public Optional<MotorEntity> findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно получить мотор: id равен null", 400);
        }
        return motorRepo.findById(id);
    }
}
