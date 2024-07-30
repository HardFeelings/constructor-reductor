package ru.vpt.constructorapp.store.entities.commercial;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    public String getTimestamp() {
        if(Objects.isNull(timestamp)){
            return null;
        }
        return timestamp;
    }
}
