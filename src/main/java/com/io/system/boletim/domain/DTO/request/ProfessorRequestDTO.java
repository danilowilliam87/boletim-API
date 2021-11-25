package com.io.system.boletim.domain.DTO.request;

import com.io.system.boletim.domain.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorRequestDTO {

    @NotEmpty(message = "preenchimento obrigatorio")
    private String nome;

    @NotEmpty(message = "preenchimento obrigatorio")
    @Email(message = "email inválido")
    private String email;

    @NotEmpty(message = "preenchimento obrigatorio")
    private List<Disciplina> disciplinas;
}
