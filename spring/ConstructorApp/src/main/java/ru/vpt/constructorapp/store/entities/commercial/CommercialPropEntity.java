package ru.vpt.constructorapp.store.entities.commercial;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commercial_prop")
public class CommercialPropEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_commercial_prop")
    private Long idCommercialProp;

    @Column(name = "number")
    private String number;

    @Column(name = "partner")
    private String partner;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "id_manager")
    private ManagerEntity manager;

    @OneToMany
    @JoinColumn(name = "id_commercial_prop")
    private List<CommercialPropItemEntity> commercialPropItems;

}
