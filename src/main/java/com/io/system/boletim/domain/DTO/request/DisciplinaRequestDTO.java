package com.io.system.boletim.domain.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaRequestDTO {

    @NotEmpty(message = "preenchimento obrigatório")
    private String nome;

    private String desc;




}
