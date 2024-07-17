package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.type.dto.ProductTypeDto;
import ru.vpt.constructorapp.api.product.type.mapper.ProductTypeMapper;
import ru.vpt.constructorapp.service.product.ProductTypeService;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.repo.product.ProductTypeRepo;

import java.util.*;
import java.util.stream.Collectors;

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
        return dtos.stream().sorted(Comparator.comparingLong(ProductTypeDto::getIdProductType)).collect(Collectors.toList());
    }

    @Override
    public ProductTypeDto getProductTypesById(Long id) {
        ProductTypeEntity entity = productTypeRepo.findById(id).orElseThrow(() -> new NotFoundException("productType with id = " + id + " not found", 404));
        return productTypeMapper.toDTO(entity);
    }

  @Override
  public ProductTypeDto saveProductType(ProductTypeDto productTypeDto) {
    if (Objects.isNull(productTypeDto)) {
      throw new BadRequestException("Невозможно сохранить тип продукта: dto равен null", 400);
    }
    ProductTypeEntity entity = productTypeMapper.toEntity(productTypeDto);
    return productTypeMapper.toDTO(productTypeRepo.save(entity));
  }

  @Override
  public Boolean deleteProductType(Long id) {
    if (Objects.isNull(id)) {
      throw new BadRequestException("Невозможно удалить тип продукта: id равен null", 400);
    }
    if (!productTypeRepo.existsById(id)) {
      throw new NotFoundException("Невозможно удалить тип продукта: не найден объект с id: " + id, 404);
    }
    productTypeRepo.deleteById(id);
    return true;
  }

  @Override
  public Optional<ProductTypeEntity> findById(Long id) {
    if (Objects.isNull(id)) {
      throw new BadRequestException("Невозможно получить тип продукта: id равен null", 400);
    }
    return productTypeRepo.findById(id);
  }
}
