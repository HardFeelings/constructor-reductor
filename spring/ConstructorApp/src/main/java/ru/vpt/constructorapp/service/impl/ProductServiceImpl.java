package ru.vpt.constructorapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.product.dto.ProductDto;
import ru.vpt.constructorapp.api.product.mapper.ProductMapper;
import ru.vpt.constructorapp.service.ProductService;
import ru.vpt.constructorapp.store.entities.ProductEntity;
import ru.vpt.constructorapp.store.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    @Override
    public List<ProductDto> getAllProduct() {
        List<ProductDto> productDtos = new ArrayList<>();
        productRepository.findAll().forEach(item -> {
            productDtos.add(productMapper.toDTO(item));
        });
        return productDtos;
    }

    @Override
    public ProductDto getById(Long id) {
        ProductEntity entity = productRepository.findById(id).get();
        if(entity == null)
            return null;
        return productMapper.toDTO(entity);
    }
}
