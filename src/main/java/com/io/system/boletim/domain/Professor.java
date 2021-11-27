package com.io.system.boletim.domain;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity(name = "PROFESSOR")
public class Professor implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(name = "PROFESSOR_DISCIPLINA",
            joinColumns = @JoinColumn(name = "idProfessor"),
            inverseJoinColumns = @JoinColumn(name = "idDisciplina"))
    private List<Disciplina>disciplinas;

}
