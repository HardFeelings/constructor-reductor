package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.service.product.ProductTypeService;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.repo.product.ProductTypeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeMapper productTypeMapper;
    private final ProductTypeRepo productTypeRepo;

    @Override
    public List<ProductTypeDto> getAllProductTypes() {
        List<ProductTypeEntity> entities = productTypeRepo.findAll();
        List<ProductTypeDto> dtos = new ArrayList<>();
        entities.forEach(item -> dtos.add(productTypeMapper.toDTO(item)));
        return dtos;
    }

    @Override
    public ProductTypeDto getProductTypesById(Long id) {
        ProductTypeEntity entity = productTypeRepo.findById(id).get();
        return productTypeMapper.toDTO(entity);
    }

  @Override
  public ProductTypeDto saveProductType(ProductTypeDto productTypeDto) {
    if (Objects.isNull(productTypeDto)) {
      throw new RuntimeException("Невозможно сохранить тип продукта: dto равен null");
    }
    ProductTypeEntity entity = productTypeMapper.toEntity(productTypeDto);
    return productTypeMapper.toDTO(productTypeRepo.save(entity));
  }

  @Override
  public Boolean deleteProductType(Long id) {
    if (Objects.isNull(id)) {
      throw new RuntimeException("Невозможно удалить тип продукта: id равен null");
    }
    if (!productTypeRepo.existsById(id)) {
      throw new RuntimeException("Невозможно удалить тип продукта: не найден объект с id: " + id);
    }
    productTypeRepo.deleteById(id);
    return true;
  }

  @Override
  public ProductTypeEntity findById(Long id) {
    if (Objects.isNull(id)) {
      throw new RuntimeException("Невозможно получить тип продукта: id равен null");
    }
    return productTypeRepo.findById(id).orElse(null);
  }
}
