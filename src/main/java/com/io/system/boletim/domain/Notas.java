package com.io.system.boletim.domain;

import com.io.system.boletim.Enums.StatusAluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "NOTAS")
public class Notas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SEMESTRE")
    private String semestre;

    @OneToOne(mappedBy = "notas")
    private Aluno aluno;

    @OneToOne
    private Disciplina disciplinas;

    private StatusAluno statusAluno;

    @Column(name = "MEDIA")
    private double nota;


}
