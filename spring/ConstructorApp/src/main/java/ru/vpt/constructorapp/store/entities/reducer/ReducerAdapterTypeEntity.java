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
@Table(name = "reducer_adapter_type")
public class ReducerAdapterTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reducer_adapter_type")
    private Long idReducerAdapterType;

    @Column(name = "reducer_adapter_type_value")
    private String reducerAdapterTypeValue;
    @ManyToOne
    @JoinColumn(name = "id_reducer_type")
    private ReducerTypeEntity reducerType;
}
