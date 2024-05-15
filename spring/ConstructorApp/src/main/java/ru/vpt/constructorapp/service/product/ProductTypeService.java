package ru.vpt.constructorapp.service.product;

import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;

import java.util.List;

public interface ProductTypeService {
    List<ProductTypeDto> getAllProductTypes();
    ProductTypeDto getProductTypesById(Long id);
    ProductTypeDto saveProductType(ProductTypeDto productTypeDto);
    Boolean deleteProductType(Long id);
    ProductTypeEntity findById(Long id);
}
