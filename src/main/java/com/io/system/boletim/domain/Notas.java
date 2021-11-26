package com.io.system.boletim.domain;

import com.io.system.boletim.Enums.StatusAluno;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity(name = "NOTAS")
public class Notas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SEMESTRE")
    private String semestre;

    @OneToOne
    @JoinColumn(name = "ALUNO_ID")
    private Aluno aluno;

    @OneToOne
    @JoinColumn(name = "DISCIPLINA_ID")
    private Disciplina disciplina;

    @Column(name = "SITUACAO")
    private StatusAluno statusAluno;

    @Column(name = "MEDIA")
    private double nota;


}
