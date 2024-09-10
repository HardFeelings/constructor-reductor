package ru.vpt.constructorapp.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.api.exception.NotFoundException;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductPaginationDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.service.motor.impl.MotorServiceImpl;
import ru.vpt.constructorapp.service.product.ProductService;
import ru.vpt.constructorapp.service.reducer.impl.ReducerServiceImpl;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;
import ru.vpt.constructorapp.store.repo.product.ProductRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepo productRepo;
    private final ProductTypeServiceImpl productTypeService;
    private final ReducerServiceImpl reducerService;
    private final MotorServiceImpl motorService;
    private final ProductOptionServiceImpl productOptionService;

    @Override
    public ProductPaginationDto getAllProducts(int offset, int limit) {
        Page<ProductEntity> page = productRepo.findAll(PageRequest.of(offset, limit, Sort.by("idProduct")));
        List<ProductDto> dtos = new ArrayList<>();
        page.getContent().forEach(item -> dtos.add(productMapper.toDTO(item)));
        ProductPaginationDto paginationDto = new ProductPaginationDto();
        paginationDto.setProductDtoList(dtos);
        paginationDto.setTotalCount(page.getTotalElements());
        paginationDto.setTotalPages(page.getTotalPages());
        paginationDto.setCurrentPage(offset);
        return paginationDto;
    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductEntity entity = productRepo.findById(id).orElseThrow(() -> new NotFoundException("product with id = " + id + " not found", 404));
        return productMapper.toDTO(entity);
    }

    @Override
    public ProductDto saveProduct(ProductDto dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException("Невозможно сохранить продукт: dto равен null", 400);
        }
        ProductEntity entity = productMapper.toEntity(dto);
        entity.setProductType(productTypeService.findById(dto.getProductTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить продукт: не найден тип продукта с id: " + dto.getProductTypeId(), 404)));
        if (dto.getReducerId() != null)
            entity.setReducer(reducerService.findById(dto.getReducerId())
                    .orElseThrow(() -> new NotFoundException("Cannot save: reducer with id = " + dto.getReducerId() + " not found", 404)));
        else entity.setReducer(null);

        if (dto.getMotorId() != null)
            entity.setMotor(motorService.findById(dto.getMotorId())
                    .orElseThrow(() -> new NotFoundException("Cannot save: motor with id = " + dto.getMotorId() + " not found", 404)));
        else entity.setMotor(null);

        if (dto.getImageChanged()) {
            if (dto.getImageString() != null)
                entity.setProductImage(Base64.getDecoder().decode(dto.getImageString()));
            else
                entity.setProductImage(null);
        } else {
            if (entity.getIdProduct() != null) {
                entity.setProductImage(productRepo.findById(entity.getIdProduct())
                        .orElseThrow(() -> new NotFoundException("Cannot save image: product with id = " + entity.getIdProduct() + " not found", 404))
                        .getProductImage());
            } else
                entity.setProductImage(dto.getImageString() == null ? null : Base64.getDecoder().decode(dto.getImageString()));
        }
        if (dto.getOptionsIds() != null) {
            entity.setOptions(dto.getOptionsIds().stream().map(item -> productOptionService.findById(item)
                            .orElseThrow(() -> new NotFoundException("Cannot save options: option with id = " + item + " not found", 404)))
                    .collect(Collectors.toSet()));
        }
        return productMapper.toDTO(productRepo.save(entity));
    }

    @Override
    public Boolean deleteProduct(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("Невозможно удалить продукт: id равен null", 400);
        }
        if (!productRepo.existsById(id)) {
            throw new NotFoundException("Невозможно удалить продукт: не найден объект с id: " + id, 404);
        }
        productRepo.deleteById(id);
        return true;
    }

    @Override
    public ProductEntity getProductEntityById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new NotFoundException("product with id = " + id + " not found", 404));

    }

    @Override
    public ProductDto dynamicSave(ProductDto productDto) {
        if (Objects.isNull(productDto)) {
            throw new BadRequestException("Невозможно сохранить продукт: dto равен null", 400);
        }
        ProductEntity productEntity = productMapper.toEntity(productDto);
        productEntity.setProductType(productTypeService.findById(productDto.getProductTypeId())
                .orElseThrow(() -> new NotFoundException("Невозможно сохранить продукт: не найден тип продукта с id: " + productDto.getProductTypeId(), 404)));
        if (productDto.getMotor() != null) {
            List<MotorEntity> motorEntities = motorService.findByFilter(productDto.getMotor());
            if (motorEntities.isEmpty()) {
                MotorEntity motorEntity = motorService.saveMotorEntity(productDto.getMotor());
                productEntity.setMotor(motorEntity);
            } else {
                productEntity.setMotor(motorEntities.get(0));
            }
        }
        if (productDto.getReducer() != null) {
            List<ReducerEntity> reducerEntities = reducerService.findByFilter(productDto.getReducer());
            if (reducerEntities.isEmpty()) {
                ReducerEntity reducerEntity = reducerService.saveReducerEntity(productDto.getReducer());
                productEntity.setReducer(reducerEntity);
            } else {
                productEntity.setReducer(reducerEntities.get(0));
            }
        }
        if (productDto.getOptionsIds() != null) {
            productEntity.setOptions(productDto.getOptionsIds().stream().map(item -> productOptionService.findById(item)
                            .orElseThrow(() -> new NotFoundException("Cannot save options: option with id = " + item + " not found", 404)))
                    .collect(Collectors.toSet()));
        }
        productEntity.setProductImage(productDto.getImageString() == null ? null : Base64.getDecoder().decode(productDto.getImageString()));
        return productMapper.toDTO(productRepo.save(productEntity));
    }

    @Override
    public List<ProductDto> getByName(String name) {
        if(Objects.isNull(name))
            name = "%";
        name = "%" + name + "%";
        return productRepo.findAllByNameLike(name).stream().map(productMapper::toDTO)
                .sorted(Comparator.comparingLong(ProductDto::getIdProduct)).collect(Collectors.toList());
    }

}
