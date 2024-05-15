package ru.vpt.constructorapp.service.product;

import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

import java.util.List;

public interface ProductOptionService {
    List<ProductOptionDto> getAllProductOptions();
    ProductOptionDto getProductOptionById(Long id);
    List<ProductOptionDto> getAllProductOptionsByProductTypeId(Long id);
    ProductOptionDto saveProductOption(ProductOptionDto productOptionDto);
    Boolean deleteProductOption(Long id);
    ProductOptionEntity findById(Long id);
}
