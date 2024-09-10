package ru.vpt.constructorapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductDto;
import ru.vpt.constructorapp.api.product.common.dto.ProductPaginationDto;
import ru.vpt.constructorapp.api.product.common.mapper.ProductMapper;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;
import ru.vpt.constructorapp.store.repo.product.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final ProductRepo repo;
    private final ProductMapper mapper;
    public ProductPaginationDto filter(FilterDto filterDto, int offset, int limit) {
        Page<ProductEntity> page = repo.findByFilter(filterDto, PageRequest.of(offset,limit));
        List<ProductDto> dtos = new ArrayList<>();
        List<ProductEntity> productEntities = page.getContent();
        productEntities.forEach(item -> dtos.add(mapper.toDTOWithRef(item)));
        ProductPaginationDto finalDto = new ProductPaginationDto();
        finalDto.setProductDtoList(dtos.stream().filter(item -> filterProductByOptions(item, filterDto.getProductOptions())).collect(Collectors.toList()));
        finalDto.setTotalCount(page.getTotalElements());
        finalDto.setCurrentPage(offset);
        finalDto.setTotalPages(page.getTotalPages());
        return finalDto;
    }

    private boolean filterProductByOptions(ProductDto dto, List<Long> ids){
        if(ids == null || ids.isEmpty())
            return true;
        for(Long id : ids){
            if(!dto.getOptionsIds().contains(id))
                return false;
        }
        return true;
    }
}
