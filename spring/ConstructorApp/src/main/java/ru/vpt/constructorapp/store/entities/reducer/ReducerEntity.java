package ru.vpt.constructorapp.store.entities.reducer;

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
@Table(name = "reducer_entity")
public class ReducerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reducer")
    private Long idReducer;

    @ManyToOne
    @JoinColumn(name = "id_reducer_type")
    private ReducerTypeEntity reducerType;

    @ManyToOne
    @JoinColumn(name = "id_reducer_size")
    private ReducerSizeEntity reducerSize;

    @ManyToOne
    @JoinColumn(name = "id_reducer_input_type")
    private ReducerInputTypeEntity reducerInputType;

    @ManyToOne
    @JoinColumn(name = "id_reducer_adapter_type")
    private ReducerAdapterTypeEntity reducerAdapterType;

    @ManyToOne
    @JoinColumn(name = "id_reducer_output_shaft_type")
    private ReducerOutputShaftTypeEntity reducerOutputShaftType;

    @ManyToOne
    @JoinColumn(name = "id_reducer_installation_type")
    private ReducerInstallationTypeEntity reducerInstallationType;

    @ManyToOne
    @JoinColumn(name = "id_reducer_mounting")
    private ReducerMountingEntity reducerMounting;

    @Column(name = "diameter_input_shaft")
    private Integer diameterInputShaft;

    @Column(name = "diameter_output_shaft")
    private Integer diameterOutputShaft;

    @Column(name = "ratio")
    private Double ratio;


}
