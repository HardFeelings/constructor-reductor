package ru.vpt.constructorapp.store.repo.filter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProductFilterRepo {
    @PersistenceContext
    EntityManager entityManager;
}
