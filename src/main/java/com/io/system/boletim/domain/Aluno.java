package com.io.system.boletim.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ALUNO")
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(cascade = { CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.DETACH})
    @JoinTable(name = "ALUNO_CURSO",
            joinColumns = @JoinColumn(name = "idAluno"),
            inverseJoinColumns = @JoinColumn(name = "idCurso"))
    private List<Curso> cursos;

}
