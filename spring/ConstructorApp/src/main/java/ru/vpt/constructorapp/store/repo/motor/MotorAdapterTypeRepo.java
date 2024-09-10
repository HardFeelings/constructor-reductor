package ru.vpt.constructorapp.store.repo.motor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.motor.MotorAdapterTypeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotorAdapterTypeRepo extends JpaRepository<MotorAdapterTypeEntity,Long> {
    List<MotorAdapterTypeEntity> findAll();
    Optional<MotorAdapterTypeEntity> findById(Long id);
    List<MotorAdapterTypeEntity> findMotorAdapterTypeEntitiesByMotorType_IdMotorType(Long id);
}
