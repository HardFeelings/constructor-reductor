package ru.vpt.constructorapp.store.entities.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vpt.constructorapp.store.entities.reducer.ReducerMountingEntity;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_option")
public class ProductOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product_option")
    private Long idProductOption;
    @Column(name = "product_option_value")
    private String productOptionValue;

    @ManyToOne
    @JoinColumn(name = "id_product_type")
    private ProductTypeEntity productType;

}
