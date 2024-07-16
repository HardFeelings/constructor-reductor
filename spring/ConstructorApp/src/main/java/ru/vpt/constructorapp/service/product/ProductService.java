package ru.vpt.constructorapp.service.product;

import org.springframework.core.io.Resource;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto saveProduct(ProductDto productDto);

    Boolean deleteProduct(Long id);

    ProductEntity getProductEntityById(Long id);
}
