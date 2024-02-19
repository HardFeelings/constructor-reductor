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
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    private Long idProduct;
    @Column(name = "product_name")
    private String productName;
    @ManyToOne
    @JoinColumn(name = "id_input_node")
    private InputNodeEntity inputNodeEntity;
    @ManyToOne
    @JoinColumn(name = "id_reducer")
    private ReducerEntity reducerEntity;
    @Column(name = "shaft_dimension")
    private Double shaftDimension;
    @Column(name = "flange_diameter")
    private Double flangeDiameter;
    @Column(name = "output_rpm")
    private Double outputRPM;
    @Column(name = "input_rpm")
    private Double inputRPM;
    @Column(name = "radial_load")
    private Double radialLoad;
    @Column(name = "service_factor")
    private Double serviceFactor;
    @Column(name = "output_torque")
    private Double outputTorque;
    @Column(name = "axial_load")
    private Double axialLoad;
    @Column(name = "motor_power")
    private Double motorPower;

}
