package ru.vpt.constructorapp.store.repo.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.product.ProductTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeRepo extends CrudRepository<ProductTypeEntity, Long> {
    List<ProductTypeEntity> findAll();
    Optional<ProductTypeEntity> findById(Long id);
}
