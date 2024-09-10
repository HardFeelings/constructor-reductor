package ru.vpt.constructorapp.store.repo.motor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotorRepo extends JpaRepository<MotorEntity,Long> {
    List<MotorEntity> findAll();
    Optional<MotorEntity> findById(Long id);

    @Query(value = "select m.*\n" +
            "from motor m\n" +
            "where case when :id_motor_adapter_type is null then m.id_motor_adapter_type is null else m.id_motor_adapter_type = :id_motor_adapter_type end\n" +
            "  and case when :id_motor_type is null then m.id_motor_type is null else m.id_motor_type = :id_motor_type end\n" +
            "  and case when :efficiency is null then m.efficiency is null else m.efficiency = :efficiency end\n" +
            "  and case when :frequency is null then m.frequency is null else m.frequency = :frequency end\n" +
            "  and case when :moment_of_inertia is null then m.moment_of_inertia is null else m.moment_of_inertia = :moment_of_inertia end\n" +
            "  and case when :power is null then m.power is null else m.power = :power end\n" +
            "    and case when :rated_current is null then m.rated_current is null else m.rated_current = :rated_current end;", nativeQuery = true)
    List<MotorEntity> findByAllParameters(
            @Param("id_motor_adapter_type") Long idMotorAdapterType,
            @Param("id_motor_type") Long idMotorType,
            @Param("efficiency") Double efficiency,
            @Param("frequency") Double frequency,
            @Param("moment_of_inertia") Double momentOfInertia,
            @Param("power") Double power,
            @Param("rated_current") Double ratedCurrent);
}
