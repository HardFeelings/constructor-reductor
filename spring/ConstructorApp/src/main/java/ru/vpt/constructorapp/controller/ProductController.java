package ru.vpt.constructorapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.product.ProductApi;
import ru.vpt.constructorapp.api.product.dto.ProductDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController extends AbstractResponseController implements ProductApi {
    private final ProductService productService;
    @Override
    public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProduct() {
        return response(productService.getAllProduct());
    }

    @Override
    public ResponseEntity<ResponseDto<ProductDto>> getById(Long id) {
        return response(productService.getById(id));
    }
}
