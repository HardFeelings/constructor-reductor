package ru.vpt.constructorapp.store.repo.motor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotorRepo extends CrudRepository<MotorEntity,Long> {
    List<MotorEntity> findAll();
    Optional<MotorEntity> findById(Long id);
}
