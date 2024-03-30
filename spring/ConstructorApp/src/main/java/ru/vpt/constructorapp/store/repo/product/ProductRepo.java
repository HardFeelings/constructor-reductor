package ru.vpt.constructorapp.store.repo.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findAll();
    Optional<ProductEntity> findById(Long id);
}
