package ru.vpt.constructorapp.service.product;

import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionPaginationDto;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

import java.util.List;
import java.util.Optional;

public interface ProductOptionService {
    ProductOptionPaginationDto getAllProductOptions(int offset, int limit);
    ProductOptionDto getProductOptionById(Long id);
    List<ProductOptionDto> getAllProductOptionsByProductTypeId(Long id);
    ProductOptionDto saveProductOption(ProductOptionDto productOptionDto);
    Boolean deleteProductOption(Long id);
    Optional<ProductOptionEntity> findById(Long id);
}
