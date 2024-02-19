package ru.vpt.constructorapp.store.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Long> {
    List<ProductEntity> findAll();
    Optional<ProductEntity> findById(Long id);
}
