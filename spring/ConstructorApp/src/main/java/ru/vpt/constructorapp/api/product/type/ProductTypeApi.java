package ru.vpt.constructorapp.api.product.type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/productType")
public interface ProductTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ProductTypeDto>>> getAllProductTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ProductTypeDto>> getById(@PathVariable("id") Long id);
}
