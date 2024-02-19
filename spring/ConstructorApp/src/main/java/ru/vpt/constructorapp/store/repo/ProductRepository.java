package ru.vpt.constructorapp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vpt.constructorapp.store.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
