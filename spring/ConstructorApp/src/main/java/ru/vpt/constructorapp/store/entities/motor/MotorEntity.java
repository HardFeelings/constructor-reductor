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
    @Column(name = "efficiency")
    private Double efficiency;
    @Column(name = "rated_current")
    private Double ratedCurrent;

    @Column(name = "moment_of_inertia")
    private Double momentOfInertia;

    @Column(name = "pos_terminal_box")
    private Double posTerminalBox;
    @ManyToOne
    @JoinColumn(name = "id_motor_type")
    private MotorTypeEntity motorType;
    @ManyToOne
    @JoinColumn(name = "id_motor_adapter_type")
    private MotorAdapterTypeEntity motorAdapterType;
}
