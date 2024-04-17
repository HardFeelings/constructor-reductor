package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.product.option.dto.ProductOptionDto;
import ru.vpt.constructorapp.api.product.option.mapper.ProductOptionMapper;
import ru.vpt.constructorapp.service.product.ProductOptionService;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.repo.product.ProductOptionRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionMapper productOptionMapper;
    private final ProductOptionRepo productOptionRepo;

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
}
