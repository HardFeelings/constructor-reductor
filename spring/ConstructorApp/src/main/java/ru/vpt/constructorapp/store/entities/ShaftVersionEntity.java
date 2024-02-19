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
@Table(name = "shaft_version")
public class ShaftVersionEntity {
    @Id
    @Column(name = "id_shaft_version")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idShaftVersion;
    @Column(name = "shaft_version_name")
    private String shaftVersionName;
    @ManyToOne
    @JoinColumn(name = "id_reducer_type")
    private ReducerTypeEntity reducerTypeEntity;
    @Column(name = "image_path")
    private String imagePath;

}
