package ru.vpt.constructorapp.store.repo.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

public interface ProductCustomRepo {
    Page<ProductEntity> findByFilter(FilterDto filter, Pageable pageable);
}
