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
@Table(name = "gearbox_version")
public class GearboxVersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_gearbox_version")
    private Long idGearboxVersion;
    @Column(name = "gearbox_version_name")
    private String gearboxVersionName;
    @ManyToOne
    @JoinColumn(name = "id_reducer_type")
    private ReducerTypeEntity reducerType;
    @Column(name = "image_path")
    private String imagePath;


}
