package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.option.mapper.ProductOptionMapper;
import ru.vpt.constructorapp.service.product.ProductOptionService;
import ru.vpt.constructorapp.service.product.ProductTypeService;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.repo.product.ProductOptionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

  private final ProductOptionMapper productOptionMapper;
  private final ProductOptionRepo productOptionRepo;
  private final ProductTypeService productTypeService;

  @Override
  public List<ProductOptionDto> getAllProductOptions() {
    List<ProductOptionEntity> entities = productOptionRepo.findAll();
    List<ProductOptionDto> dtos = new ArrayList<>();
    entities.forEach(item -> dtos.add(productOptionMapper.toDTO(item)));
    return dtos;
  }

  @Override
  public ProductOptionDto getProductOptionById(Long id) {
    ProductOptionEntity entity = productOptionRepo.findById(id).get();
    return productOptionMapper.toDTO(entity);
  }

  @Override
  public List<ProductOptionDto> getAllProductOptionsByProductTypeId(Long id) {
    List<ProductOptionEntity> entities = productOptionRepo.findProductOptionEntitiesByProductType_IdProductType(id);
    List<ProductOptionDto> dtos = new ArrayList<>();
    entities.forEach(item -> dtos.add(productOptionMapper.toDTO(item)));
    return dtos;
  }

  @Override
  public ProductOptionDto saveProductOption(ProductOptionDto dto) {
    if (Objects.isNull(dto)) {
      throw new RuntimeException("Невозможно сохранить опции продукта: dto равен null");
    }
    ProductTypeEntity productTypeEntity = productTypeService.findById(dto.getProductTypeId());
    if (Objects.isNull(productTypeEntity)) {
      throw new RuntimeException("Невозможно сохранить опции продукта: не найден тип продукта с id: " + dto.getProductTypeId());
    }
    ProductOptionEntity entity = productOptionMapper.toEntity(dto);
    entity.setProductType(productTypeEntity);
    return productOptionMapper.toDTO(productOptionRepo.save(entity));
  }

  @Override
  public Boolean deleteProductOption(Long id) {
    if (Objects.isNull(id)) {
      throw new RuntimeException("Невозможно удалить опции продукта: id равен null");
    }
    if (!productOptionRepo.existsById(id)) {
      throw new RuntimeException("Невозможно удалить опции продукта: не найден объект с id: " + id);
    }
    productOptionRepo.deleteById(id);
    return true;
  }

  @Override
  public ProductOptionEntity findById(Long id) {
    if (Objects.isNull(id)) {
      throw new RuntimeException("Невозможно получить опции продукта: id равен null");
    }
    return productOptionRepo.findById(id).orElse(null);
  }
}
