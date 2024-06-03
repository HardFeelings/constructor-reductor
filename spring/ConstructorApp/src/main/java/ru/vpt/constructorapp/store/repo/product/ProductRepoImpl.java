package ru.vpt.constructorapp.store.repo.product;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ru.vpt.constructorapp.api.filter.dto.FilterDto;
import ru.vpt.constructorapp.api.util.QPredicates;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

import java.util.List;

import static ru.vpt.constructorapp.store.entities.product.QProductEntity.productEntity;
@RequiredArgsConstructor
public class ProductRepoImpl implements ProductCustomRepo{
    private final EntityManager entityManager;
    @Override
    public List<ProductEntity> findByFilter(FilterDto filter) {
        return new JPAQuery<ProductEntity>(entityManager)
                .select(productEntity)
                .from(productEntity)
                .where(QPredicates.builder()
                        .add(filter.getProductTypeId(), productEntity.productType.idProductType::eq)
                        .buildAnd())
                .fetch();
    }
}
