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
@Table(name = "motor")
public class MotorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_motor")
    private Long idMotor;
    @Column(name = "power")
    private Double power;
    @Column(name = "frequency")
    private Double frequency;
    @Column(name = "rpm")
    private Double rpm;
    @ManyToOne
    @JoinColumn(name = "id_motor_type")
    private MotorTypeEntity motorType;
    @ManyToOne
    @JoinColumn(name = "id_motor_adapter_type")
    private MotorAdapterTypeEntity motorAdapterType;
}
