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
@Table(name = "motor_type")
public class MotorTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_motor_type")
    private Long idMotorType;
    @Column(name = "motor_type_name")
    private String motorTypeName;
}
