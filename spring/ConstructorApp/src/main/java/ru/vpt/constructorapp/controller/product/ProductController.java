package ru.vpt.constructorapp.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vpt.constructorapp.api.product.common.ProductApi;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.util.ResponseDto;
import ru.vpt.constructorapp.controller.util.AbstractController;
import ru.vpt.constructorapp.service.product.ProductService;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController extends AbstractController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts() {
        return response(productService.getAllProducts());
    }

    @Override
    public ResponseEntity<ResponseDto<ProductDto>> getById(Long id) {
        return response(productService.getProductById(id));
    }

    @Override
    public ResponseEntity<ResponseDto<List<ProductDto>>> getByName(String name) {
        return response(productService.getByName(name));
    }

    @Override
    public ResponseEntity<ResponseDto<ProductDto>> save(ProductDto productDto) {
        return response(productService.saveProduct(productDto));
    }

    @Override
    public ResponseEntity<ResponseDto<ProductDto>> dynamicSave(ProductDto productDto) {
        return response(productService.dynamicSave(productDto));
    }

    @Override
    public ResponseEntity<ResponseDto<Boolean>> delete(Long id) {
        return response(productService.deleteProduct(id));
    }

    @Override
    public ResponseEntity<Resource> getImage(Long id) {
        ProductEntity entity = productService.getProductEntityById(id);
        ByteArrayResource bar = new ByteArrayResource(entity.getProductImage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + entity.getName() + ".jpeg");
        return new ResponseEntity<>(bar, headers, HttpStatus.OK);
    }
}
