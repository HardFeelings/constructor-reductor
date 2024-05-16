package ru.vpt.constructorapp.api.product.option;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping("/security/productOption")
public interface ProductOptionApi {
    @GetMapping
    ResponseEntity<ResponseDto<List<ProductOptionDto>>> getAllProductOptions();

    @GetMapping("/{id}")
    ResponseEntity<ResponseDto<ProductOptionDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byProductTypeId/{id}")
    ResponseEntity<ResponseDto<List<ProductOptionDto>>> getByProductTypeId(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ResponseDto<ProductOptionDto>> save(@RequestBody ProductOptionDto productOptionDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable Long id);
}
