package ru.vpt.constructorapp.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.product.type.ProductTypeApi;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.product.ProductTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductTypeController extends AbstractController implements ProductTypeApi {

    private final ProductTypeService productTypeService;

    @Override
    public ResponseEntity<ResponseDto<List<ProductTypeDto>>> getAllProductTypes() {
        return response(productTypeService.getAllProductTypes());
    }

    @Override
    public ResponseEntity<ResponseDto<ProductTypeDto>> getById(Long id) {
        return response(productTypeService.getProductTypesById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<ProductTypeDto>> save(ProductTypeDto productTypeDto) {
        return response(productTypeService.saveProductType(productTypeDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(productTypeService.deleteProductType(id));
    }
}
