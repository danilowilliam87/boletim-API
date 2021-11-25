package com.io.system.boletim.domain.DTO.request;

import com.io.system.boletim.domain.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequestDTO {

    @NotEmpty(message = "preenchimento obrigatório")
    private String nome;

    @NotEmpty(message = "preenchimento obrigatório")
    @Email(message = "preenchimento obrigatorio")
    private String email;

    @NotEmpty(message = "preenchimento obrigatório")
    private List<Curso> cursos;
}
