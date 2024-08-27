package ru.vpt.constructorapp.store.entities.commercial;


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
@Table(name = "commercial_prop_terms")
public class CommercialPropTermsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_commercial_prop_terms")
    private Long idCommercialPropTerms;

    @Column(name = "ord")
    private Integer ord;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "days")
    private Integer days;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_commercial_prop")
    private CommercialPropEntity commercialProp;

    @ManyToOne()
    @JoinColumn(name = "id_payment_terms")
    private PaymentTermsEntity paymentTerms;
}
