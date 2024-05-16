package ru.vpt.constructorapp.api.product.type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/productType")
public interface ProductTypeApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ProductTypeDto>>> getAllProductTypes();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ProductTypeDto>> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ProductTypeDto>> save(@RequestBody ProductTypeDto productTypeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
