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
@Table(name = "reducer_mounting")
public class ReducerMountingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reducer_mounting")
    private Long idReducerMounting;

    @Column(name = "reducer_mounting_value")
    private String reducerMountingValue;
}
