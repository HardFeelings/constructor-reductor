package ru.vpt.constructorapp.api.product.option;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface ProductOptionApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/productOption")
    ResponseEntity<ResponseDto<ProductOptionPaginationDto>> getAllProductOptions(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                                 @RequestParam(value = "limit", defaultValue = "15") Integer limit);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/productOption/{id}")
    ResponseEntity<ResponseDto<ProductOptionDto>> getById(@PathVariable("id") Long id);

    @GetMapping("/byProductTypeId/{id}")
    ResponseEntity<ResponseDto<List<ProductOptionDto>>> getByProductTypeId(@PathVariable("id") Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/productOption")
    ResponseEntity<ResponseDto<ProductOptionDto>> save(@RequestBody ProductOptionDto productOptionDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/productOption/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable Long id);
}
