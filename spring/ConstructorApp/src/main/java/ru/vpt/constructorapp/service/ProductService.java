package ru.vpt.constructorapp.service;

import ru.vpt.constructorapp.api.product.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProduct();
    ProductDto getById(Long id);
}
