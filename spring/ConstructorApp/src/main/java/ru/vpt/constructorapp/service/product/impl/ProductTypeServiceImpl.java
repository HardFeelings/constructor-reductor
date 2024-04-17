package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.service.product.ProductTypeService;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.repo.product.ProductTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeMapper productTypeMapper;
    private final ProductTypeRepo productTypeRepo;

    @Override
    public List<ProductTypeDto> getAllProductTypes() {
        List<ProductTypeEntity> entities = productTypeRepo.findAll();
        List<ProductTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(productTypeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ProductTypeDto getProductTypesById(Long id) {
        ProductTypeEntity entity = productTypeRepo.findById(id).get();
        return productTypeMapper.toDTO(entity);
    }
}
