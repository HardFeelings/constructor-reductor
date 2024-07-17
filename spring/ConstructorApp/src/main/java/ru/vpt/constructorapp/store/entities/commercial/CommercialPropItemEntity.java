package ru.vpt.constructorapp.store.entities.commercial;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vpt.constructorapp.store.entities.product.ProductEntity;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commercial_prop_item")
public class CommercialPropItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_commercial_prop_item")
    private Long idCommercialPropItem;

    @ManyToOne
    @JoinColumn(name = "id_commercial_prop")
    private CommercialPropEntity commercialProp;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    @Column(name = "amount")
    private Integer amount;
}
