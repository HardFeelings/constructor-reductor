package ru.vpt.constructorapp.service.product;

import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;

import java.util.List;

public interface ProductTypeService {
    List<ProductTypeDto> getAllProductTypes();
    ProductTypeDto getProductTypesById(Long id);
}
