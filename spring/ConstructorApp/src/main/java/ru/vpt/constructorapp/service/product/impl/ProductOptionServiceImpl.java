package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionPaginationDto;
import ru.vpt.constructorapp.api.product.option.mapper.ProductOptionMapper;
import ru.vpt.constructorapp.service.product.ProductOptionService;
import ru.vpt.constructorapp.service.product.ProductTypeService;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;
import ru.vpt.constructorapp.store.repo.product.ProductOptionRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

  private final ProductOptionMapper productOptionMapper;
  private final ProductOptionRepo productOptionRepo;
  private final ProductTypeService productTypeService;

  @Override
  public ProductOptionPaginationDto getAllProductOptions(int offset, int limit) {
    Page<ProductOptionEntity> page = productOptionRepo.findAll(PageRequest.of(offset, limit, Sort.by("idProductOption")));
    List<ProductOptionDto> dtos = new ArrayList<>();
    page.getContent().forEach(item -> dtos.add(productOptionMapper.toDTO(item)));
    ProductOptionPaginationDto paginationDto = new ProductOptionPaginationDto();
    paginationDto.setContent(dtos);
    paginationDto.setTotalCount(page.getTotalElements());
    paginationDto.setTotalPages(page.getTotalPages());
    paginationDto.setCurrentPage(offset);
    return paginationDto;
  }

  @Override
  public ProductOptionDto getProductOptionById(Long id) {
    ProductOptionEntity entity = productOptionRepo.findById(id).orElseThrow(() -> new NotFoundException("productOption with id = " + id + " not found", 404));
    return productOptionMapper.toDTO(entity);
  }

  @Override
  public List<ProductOptionDto> getAllProductOptionsByProductTypeId(Long id) {
    List<ProductOptionEntity> entities = productOptionRepo.findProductOptionEntitiesByProductType_IdProductType(id);
    List<ProductOptionDto> dtos = new ArrayList<>();
    entities.forEach(item -> dtos.add(productOptionMapper.toDTO(item)));
    return dtos.stream().sorted(Comparator.comparingLong(ProductOptionDto::getIdProductOption)).collect(Collectors.toList());
  }

  @Override
  public ProductOptionDto saveProductOption(ProductOptionDto dto) {
    if (Objects.isNull(dto)) {
      throw new BadRequestException("Невозможно сохранить опции продукта: dto равен null", 400);
    }
    ProductTypeEntity productTypeEntity = productTypeService.findById(dto.getProductTypeId())
            .orElseThrow(() -> new NotFoundException("Невозможно сохранить опции продукта: не найден тип продукта с id: " + dto.getProductTypeId(), 404));
    ProductOptionEntity entity = productOptionMapper.toEntity(dto);
    entity.setProductType(productTypeEntity);
    return productOptionMapper.toDTO(productOptionRepo.save(entity));
  }

  @Override
  public Boolean deleteProductOption(Long id) {
    if (Objects.isNull(id)) {
      throw new BadRequestException("Невозможно удалить опции продукта: id равен null", 400);
    }
    if (!productOptionRepo.existsById(id)) {
      throw new NotFoundException("Невозможно удалить опции продукта: не найден объект с id: " + id, 404);
    }
    productOptionRepo.deleteById(id);
    return true;
  }

  @Override
  public Optional<ProductOptionEntity> findById(Long id) {
    if (Objects.isNull(id)) {
      throw new BadRequestException("Невозможно получить опции продукта: id равен null", 400);
    }
    return productOptionRepo.findById(id);
  }
}
