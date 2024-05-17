package ru.vpt.constructorapp.api.product.type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ProductTypeApi {
    @GetMapping("/productType")
    ResponseEntity<ResponseDto<List<ProductTypeDto>>> getAllProductTypes();

    @GetMapping("/productType/{id}")
    ResponseEntity<ResponseDto<ProductTypeDto>> getById(@PathVariable("id") Long id);

    @PostMapping("/security/productType")
    ResponseEntity<ResponseDto<ProductTypeDto>> save(@RequestBody ProductTypeDto productTypeDto);

    @DeleteMapping("/security/productType/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);
}
