package ru.vpt.constructorapp.api.product.common;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface ProductApi {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/product")
    ResponseEntity<ResponseDto<ProductPaginationDto>> getAllProducts(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                     @RequestParam(value = "limit", defaultValue = "15") Integer limit);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/product/{id}")
    ResponseEntity<ResponseDto<ProductDto>> getById(@PathVariable("id") Long id);
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/security/product/getByName")
    ResponseEntity<ResponseDto<ProductPaginationDto>> getByName(@RequestParam("name") String name,
                                                                @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                                @RequestParam(value = "limit", defaultValue = "15") Integer limit);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/product")
    ResponseEntity<ResponseDto<ProductDto>> save(@RequestBody ProductDto productDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/security/product/dynamicSave")
    ResponseEntity<ResponseDto<ProductDto>> dynamicSave(@RequestBody ProductDto productDto);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/security/product/{id}")
    ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id);

    @GetMapping("/security/product/downloadImage/{id}")
    ResponseEntity<Resource> getImage(@PathVariable("id") Long id);

}
