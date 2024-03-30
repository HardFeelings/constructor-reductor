package ru.vpt.constructorapp.store.repo.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOptionRepo extends CrudRepository<ProductOptionEntity, Long> {
    List<ProductOptionEntity> findAll();
    Optional<ProductOptionEntity> findById(Long id);
    List<ProductOptionEntity> findProductOptionEntitiesByProductType_IdProductType(Long id);
}
