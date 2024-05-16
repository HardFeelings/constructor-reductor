package ru.vpt.constructorapp.api.product.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/product")
public interface ProductApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ProductDto>> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ProductDto>> save(@RequestBody ProductDto productDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);

}
