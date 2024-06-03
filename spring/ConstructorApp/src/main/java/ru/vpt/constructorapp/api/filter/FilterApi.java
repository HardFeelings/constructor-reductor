package ru.vpt.constructorapp.api.filter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

import java.util.List;

@RequestMapping
public interface FilterApi {
    @PostMapping("/filter")
    ResponseEntity<ResponseDto<List<ProductDto>>> filter(@RequestBody FilterDto filterDto);
}
