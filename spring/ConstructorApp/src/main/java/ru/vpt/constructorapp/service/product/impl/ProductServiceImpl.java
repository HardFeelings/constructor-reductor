package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.service.motor.impl.MotorServiceImpl;
import ru.vpt.constructorapp.service.product.ProductService;
import ru.vpt.constructorapp.service.reducer.impl.ReducerServiceImpl;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;
import ru.vpt.constructorapp.store.repo.product.ProductRepo;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepo productRepo;
    private final ProductTypeServiceImpl productTypeService;
    private final ReducerServiceImpl reducerService;
    private final MotorServiceImpl motorService;
    private final ProductOptionServiceImpl productOptionService;

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductEntity> entities = productRepo.findAll();
        List<ProductDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(productMapper.toDTO(item)));
        return dtos.stream().sorted(Comparator.comparingLong(ProductDto::getIdProduct)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductEntity entity = productRepo.findById(id).get();
        return productMapper.toDTO(entity);
    }

    @Override
    public ProductDto saveProduct(ProductDto dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Невозможно сохранить продукт: dto равен null");
        }
        ProductEntity entity = productMapper.toEntity(dto);
        entity.setProductType(productTypeService.findById(dto.getProductTypeId())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить продукт: не найден тип продукта с id: " + dto.getProductTypeId())));
//        if (dto.getReducerId() != null)
//            entity.setReducer(reducerService.findById(dto.getReducerId()).get());
        if (dto.getReducer().getIdReducer() != null)
            entity.setReducer(reducerService.findById(dto.getReducer().getIdReducer()).get());
        else entity.setReducer(null);

//        if (dto.getMotorId() != null)
//            entity.setMotor(motorService.findById(dto.getMotorId()).get());
        if (dto.getMotor().getIdMotor() != null)
            entity.setMotor(motorService.findById(dto.getMotor().getIdMotor()).get());
        else entity.setMotor(null);

        entity.setOptions(dto.getOptionsIds().stream().map(item -> productOptionService.findById(item).get()).collect(Collectors.toSet()));
        return productMapper.toDTO(productRepo.save(entity));
    }

    @Override
    public Boolean deleteProduct(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Невозможно удалить продукт: id равен null");
        }
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Невозможно удалить продукт: не найден объект с id: " + id);
        }
        productRepo.existsById(id);
        return true;
    }
}
