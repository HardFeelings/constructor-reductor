package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.service.product.ProductService;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;
import ru.vpt.constructorapp.store.repo.product.ProductRepo;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

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

}
