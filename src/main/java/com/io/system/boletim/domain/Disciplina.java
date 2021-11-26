package com.io.system.boletim.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "DISCIPLINA")
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String desc;

    @JsonIgnore
    @ManyToMany(mappedBy = "disciplinas")
    private List<Curso>cursos;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "DISCIPLINA_PROFESSOR",
            joinColumns = @JoinColumn(name = "idDisciplina"),
            inverseJoinColumns = @JoinColumn(name = "idProfessor"))
    private List<Professor>professores;



}
