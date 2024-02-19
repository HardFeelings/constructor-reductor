package ru.vpt.constructorapp.store.entities;

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
@Table(name = "reducer")
public class ReducerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reducer")
    private Long idReducer;
    @Column(name = "gear_ratio")
    private Double gearRatio;
    @ManyToOne
    @JoinColumn(name = "id_gearbox_version")
    private GearboxVersionEntity gearboxVersion;
    @ManyToOne
    @JoinColumn(name = "id_shaft_version")
    private ShaftVersionEntity shaftVersion;
    @Column(name = "image_path")
    private String imagePath;

}
