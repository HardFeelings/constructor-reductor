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
@Table(name = "reducer_type")
public class ReducerTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_reducer_type")
    private Long idReducerType;
    @Column(name = "reducer_type_name")
    private String reducerTypeName;
}
