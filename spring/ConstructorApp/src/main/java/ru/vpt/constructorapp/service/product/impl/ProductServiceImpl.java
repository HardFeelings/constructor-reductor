package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.service.motor.impl.MotorServiceImpl;
import ru.vpt.constructorapp.service.product.ProductService;
import ru.vpt.constructorapp.service.reducer.impl.ReducerServiceImpl;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;
import ru.vpt.constructorapp.store.repo.product.ProductRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return dtos;
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
        ProductTypeEntity productType = productTypeService.findById(dto.getProductType().getIdProductType())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить продукт: не найден тип продукта с id: " + dto.getProductType().getIdProductType()));
        ReducerEntity reducer = reducerService.findById(dto.getReducer().getIdReducer())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить продукт: не найден редуктор с id: " + dto.getReducer().getIdReducer()));
        MotorEntity motor = motorService.findById(dto.getMotor().getIdMotor())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить продукт: не найден мотор с id: " + dto.getMotor().getIdMotor()));
        ProductOptionEntity productOption = productOptionService.findById(dto.getProductOption().getIdProductOption())
                .orElseThrow(() -> new RuntimeException("Невозможно сохранить продукт: не найдены опции продукта с id: " + dto.getProductOption().getIdProductOption()));
        ProductEntity entity = productMapper.toEntity(dto);
        entity.setProductType(productType);
        entity.setReducer(reducer);
        entity.setMotor(motor);
        entity.setProductOption(productOption);
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
        productRepo.deleteById(id);
        return true;
    }
}
