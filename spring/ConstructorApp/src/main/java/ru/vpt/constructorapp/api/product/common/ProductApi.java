package ru.vpt.constructorapp.api.product.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/product")
public interface ProductApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ProductDto>> getById(@PathVariable("id") Long id);
}
