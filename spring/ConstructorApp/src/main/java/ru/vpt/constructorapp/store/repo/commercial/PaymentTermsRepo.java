package ru.vpt.constructorapp.store.repo.commercial;

import org.springframework.data.repository.CrudRepository;
import ru.vpt.constructorapp.store.entities.commercial.PaymentTermsEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentTermsRepo extends CrudRepository<PaymentTermsEntity, Long> {
    List<PaymentTermsEntity> findAll();
    Optional<PaymentTermsEntity> findById(Long id);
}
