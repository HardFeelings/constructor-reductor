package ru.vpt.constructorapp.api.product.option;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("api/v1/productOption")
public interface ProductOptionApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ProductOptionDto>>> getAllProductOptions();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ProductOptionDto>> getById(@PathVariable("id") Long id);
}
