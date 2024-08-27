package ru.vpt.constructorapp.store.entities.commercial;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "margin_ratio")
    private Double marginRatio;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "delivery_time")
    private Integer deliveryTime;

    @Column(name = "guarantee")
    private Integer guarantee;

    @Column(name = "delivery_terms")
    private String deliveryTerms;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Getter(AccessLevel.NONE)
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "id_manager")
    private ManagerEntity manager;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_commercial_prop")
    private List<CommercialPropItemEntity> commercialPropItems;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_commercial_prop")
    private List<CommercialPropTermsEntity> commercialPropTerms;

    public String getTimestamp() {
        if(Objects.isNull(timestamp)){
            return null;
        }
        return timestamp;
    }
}
