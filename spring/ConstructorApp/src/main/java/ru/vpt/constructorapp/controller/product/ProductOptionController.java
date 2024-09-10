package ru.vpt.constructorapp.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.product.option.ProductOptionApi;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionPaginationDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.product.ProductOptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductOptionController extends AbstractController implements ProductOptionApi {

    private final ProductOptionService productOptionService;

    @Override
    public ResponseEntity<ResponseDto<ProductOptionPaginationDto>> getAllProductOptions(Integer offset, Integer limit) {
        return response(productOptionService.getAllProductOptions(offset, limit));
    }

    @Override
    public ResponseEntity<ResponseDto<ProductOptionDto>> getById(Long id) {
        return response(productOptionService.getProductOptionById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<ProductOptionDto>>> getByProductTypeId(Long id) {
        return response(productOptionService.getAllProductOptionsByProductTypeId(id));
    }

  @Override
  public ResponseEntity<ResponseDto<ProductOptionDto>> save(ProductOptionDto productOptionDto) {
    return response(productOptionService.saveProductOption(productOptionDto));
  }

  @Override
  public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
    return response(productOptionService.deleteProductOption(id));
  }
}
