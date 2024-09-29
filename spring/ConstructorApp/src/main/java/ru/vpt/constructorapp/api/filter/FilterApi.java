package ru.vpt.constructorapp.api.filter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;

@RequestMapping
public interface FilterApi {
    @PostMapping("/filter")
    ResponseEntity<ResponseDto<ProductPaginationDto>> filter(@RequestBody FilterDto filterDto,
                                                             @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                             @RequestParam(value = "limit", defaultValue = "15") Integer limit);
}
