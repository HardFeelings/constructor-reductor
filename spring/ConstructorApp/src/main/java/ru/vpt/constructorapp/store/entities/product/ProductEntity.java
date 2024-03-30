package ru.vpt.constructorapp.store.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

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

    @ManyToOne
    @JoinColumn(name = "id_product_type")
    private ProductTypeEntity productType;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_reducer")
    private ReducerEntity reducer;

    @ManyToOne
    @JoinColumn(name = "id_motor")
    private MotorEntity motor;

    @ManyToOne
    @JoinColumn(name = "id_product_option")
    private ProductOptionEntity productOption;
}
