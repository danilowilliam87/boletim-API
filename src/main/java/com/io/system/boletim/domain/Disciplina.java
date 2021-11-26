package com.io.system.boletim.domain;

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

    @ManyToMany(mappedBy = "disciplinas")
    private List<Curso>cursos;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Professor>professores;



}
