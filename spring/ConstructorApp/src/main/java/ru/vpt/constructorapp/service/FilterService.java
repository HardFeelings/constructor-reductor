package ru.vpt.constructorapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;
import ru.vpt.constructorapp.store.repo.product.ProductRepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final ProductRepo repo;
    private final ProductMapper mapper;
    public List<ProductDto> filter(FilterDto filterDto) {
        List<ProductEntity> entityList = repo.findByFilter(filterDto);
        List<ProductDto> dtos = new ArrayList<>();
        entityList.forEach(item -> dtos.add(mapper.toDTO(item)));

        return dtos.stream().filter(item -> filterProductByOptions(item, filterDto.getProductOptions())).sorted(Comparator.comparingLong(ProductDto::getIdProduct)).collect(Collectors.toList());
    }

    private boolean filterProductByOptions(ProductDto dto, List<Long> ids){
        for(Long id : ids){
            if(!dto.getOptionsIds().contains(id))
                return false;
        }
        return true;
    }
}
