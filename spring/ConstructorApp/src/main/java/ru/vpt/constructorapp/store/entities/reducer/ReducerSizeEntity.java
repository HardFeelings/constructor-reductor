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
@Table(name = "reducer_size")
public class ReducerSizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reducer_size")
    private Long idReducerSize;
    @Column(name = "reducer_size_value")
    private String reducerSizeValue;
    @ManyToOne
    @JoinColumn(name = "id_reducer_type")
    private ReducerTypeEntity reducerType;
}
