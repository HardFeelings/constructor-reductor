package ru.vpt.constructorapp.store.entities.product;

import jakarta.persistence.*;
import lombok.*;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

import java.util.Set;

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

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_product_type")
    private ProductTypeEntity productType;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "price")
    private Double price;

    @Column(name = "rpm")
    private Double rpm;

    @Column(name = "torque_moment")
    private Double torqueMoment;

    @Column(name = "service_factor")
    private Double serviceFactor;

    @ManyToOne
    @JoinColumn(name = "id_reducer")
    private ReducerEntity reducer;

    @ManyToOne
    @JoinColumn(name = "id_motor")
    private MotorEntity motor;

    @ManyToMany
    @JoinTable(
            name = "products_options",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_product_option"))
    private Set<ProductOptionEntity> options;

    @Column(name = "product_image")
    private byte[] productImage;

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean isImageEmpty;

    public Boolean getImageEmpty() {
        if(productImage == null)
            return true;
        if(productImage.length > 0)
            return false;
        else
            return true;
    }
}
