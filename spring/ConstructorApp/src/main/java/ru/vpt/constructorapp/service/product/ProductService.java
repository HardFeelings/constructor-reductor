package ru.vpt.constructorapp.service.product;

import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductPaginationDto;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

public interface ProductService {
    ProductPaginationDto getAllProducts(int offset, int limit);

    ProductDto getProductById(Long id);

    ProductDto saveProduct(ProductDto productDto);

    Boolean deleteProduct(Long id);

    ProductEntity getProductEntityById(Long id);

    ProductDto dynamicSave(ProductDto productDto);

    ProductPaginationDto getByName(String name, int offset, int limit);
}
