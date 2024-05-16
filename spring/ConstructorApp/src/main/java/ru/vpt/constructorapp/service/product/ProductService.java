package ru.vpt.constructorapp.service.product;

import ru.vpt.constructorapp.api.product.common.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto saveProduct(ProductDto productDto);

    Boolean deleteProduct(Long id);
}
