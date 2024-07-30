package ru.vpt.constructorapp.api.product.common;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ProductApi {
    @GetMapping("/product")
    ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts();

    @GetMapping("/product/{id}")
    ResponseEntity<ResponseDto<ProductDto>> getById(@PathVariable("id") Long id);
    @GetMapping("/product/getByName")
    ResponseEntity<ResponseDto<List<ProductDto>>> getByName(@RequestParam("name") String name);

    @PostMapping("/security/product")
    ResponseEntity<ResponseDto<ProductDto>> save(@RequestBody ProductDto productDto);

    @PostMapping("/security/product/dynamicSave")
    ResponseEntity<ResponseDto<ProductDto>> dynamicSave(@RequestBody ProductDto productDto);

    @DeleteMapping("/security/product/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);

    @GetMapping("/product/downloadImage/{id}")
    ResponseEntity<Resource> getImage(@PathVariable("id") Long id);

}
