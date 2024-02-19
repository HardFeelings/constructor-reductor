package ru.vpt.constructorapp.store.entities;

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
@Table(name = "input_node")
public class InputNodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_input_node")
    private Long idInputNode;
    @Column(name = "input_node_name")
    private String inputNodeName;
    @Column(name = "image_path")
    private String imagePath;
}
