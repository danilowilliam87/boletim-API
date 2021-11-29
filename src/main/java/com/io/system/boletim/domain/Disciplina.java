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

    @ManyToMany(cascade = { CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.DETACH},
                 fetch = FetchType.EAGER)
    @JoinTable(name = "DISCIPLINA_CURSO",
            joinColumns = @JoinColumn(name = "idDisciplina"),
            inverseJoinColumns = @JoinColumn(name = "idCurso"))
    private List<Curso>cursos;






}
