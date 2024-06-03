package ru.vpt.constructorapp.store.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long>, ProductCustomRepo,
        QuerydslPredicateExecutor<ProductEntity> {
    List<ProductEntity> findAll();
    Optional<ProductEntity> findById(Long id);
}
