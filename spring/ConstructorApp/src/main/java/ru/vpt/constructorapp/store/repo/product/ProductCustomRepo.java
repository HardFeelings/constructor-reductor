package ru.vpt.constructorapp.store.repo.product;

import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

import java.util.List;

public interface ProductCustomRepo {
    List<ProductEntity> findByFilter(FilterDto filter);
}
