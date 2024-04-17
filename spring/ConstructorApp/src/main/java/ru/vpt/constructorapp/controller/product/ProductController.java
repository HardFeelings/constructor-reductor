package ru.vpt.constructorapp.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.product.common.ProductApi;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.product.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController extends AbstractController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts() {
        return response(productService.getAllProducts());
    }

    @Override
    public ResponseEntity<ResponseDto<ProductDto>> getById(Long id) {
        return response(productService.getProductById(id));
    }
}
