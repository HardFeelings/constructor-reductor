package ru.vpt.constructorapp.store.entities.commercial;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commercial_prop")
public class CommercialProp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_comercial_prop")
    private Long idCommercialProp;

    @Column(name = "number")
    private Integer number;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "id_manager")
    private ManagerEntity manager;

    @ManyToMany
    @JoinTable(
            name = "commercial_prop_product",
            joinColumns = @JoinColumn(name = "id_comercial_prop"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    private Set<ProductOptionEntity> options;
}
