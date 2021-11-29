package com.io.system.boletim.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "CURSO")
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "QUANTIDADE_DE_SEMESTRES")
    private int quantidadeDeSemestres;

    //@ManyToMany(mappedBy = "cursos")
    //private List<Disciplina>discipinas;

    //@ManyToMany(mappedBy = "cursos")
    //private List<Aluno>alunos;
}
