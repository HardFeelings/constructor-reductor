package ru.vpt.constructorapp.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.filter.FilterApi;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.FilterService;

@RestController
@RequiredArgsConstructor
public class FilterContoller extends AbstractController implements FilterApi {
    private final FilterService service;
    @Override
    public ResponseEntity<ResponseDto<ProductPaginationDto>> filter(FilterDto filterDto, Integer offset, Integer limit) {
        return response(service.filter(filterDto, offset, limit));
    }
}
