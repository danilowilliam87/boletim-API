package com.io.system.boletim.domain.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotasRequestDTO {

    @NotEmpty(message = "preenchimento obrigatório")
    private String semestre;

    @NotEmpty(message = "preenchimento obrigatório")
    private String nomeDoAluno;

    @NotEmpty(message = "preenchimento obrigatório")
    @Email(message = "email invalido")
    private String emailDoAluno;

    @NotEmpty(message = "preenchimento obrigatório")
    private String nomeDaDisciplina;

    @NotEmpty(message = "preenchimento obrigatório")
    private double nota;


}
