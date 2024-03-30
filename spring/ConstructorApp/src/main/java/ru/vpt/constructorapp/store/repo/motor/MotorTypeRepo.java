package ru.vpt.constructorapp.store.repo.motor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.motor.MotorTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotorTypeRepo extends CrudRepository<MotorTypeEntity, Long> {
    List<MotorTypeEntity> findAll();
    Optional<MotorTypeEntity> findById(Long id);
}
