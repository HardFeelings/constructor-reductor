package ru.vpt.constructorapp.store.entities.motor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "motor_adapter_type")
public class MotorAdapterTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_motor_adapter_type")
    private Long idMotorAdapterType;
    @Column(name = "motor_adapter_type_value")
    private String motorAdapterTypeValue;
    @ManyToOne
    @JoinColumn(name = "id_motor_type")
    private MotorTypeEntity motorType;
}
