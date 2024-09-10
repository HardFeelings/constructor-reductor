package ru.vpt.constructorapp.api.product.option;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ProductOptionApi {
    @GetMapping("/productOption")
    ResponseEntity<ResponseDto<ProductOptionPaginationDto>> getAllProductOptions(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                 @RequestParam(value = "limit", defaultValue = "20") Integer limit);

    @GetMapping("/productOption/{id}")
    ResponseEntity<ResponseDto<ProductOptionDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byProductTypeId/{id}")
    ResponseEntity<ResponseDto<List<ProductOptionDto>>> getByProductTypeId(@PathVariable("id") Long id);

    @PostMapping("/security/productOption")
    ResponseEntity<ResponseDto<ProductOptionDto>> save(@RequestBody ProductOptionDto productOptionDto);

    @DeleteMapping("/security/productOption/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable Long id);
}
