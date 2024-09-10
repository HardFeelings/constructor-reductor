package ru.vpt.constructorapp.store.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOptionRepo extends JpaRepository<ProductOptionEntity, Long> {
    List<ProductOptionEntity> findAll();
    Optional<ProductOptionEntity> findById(Long id);
    List<ProductOptionEntity> findProductOptionEntitiesByProductType_IdProductType(Long id);
}
