package com.io.system.boletim.service;

import com.io.system.boletim.Enums.StatusAluno;
import com.io.system.boletim.domain.Notas;
import com.io.system.boletim.repository.NotasRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotasServices {

    private final NotasRepo notasRepo;

    public NotasServices(NotasRepo notasRepo) {
        this.notasRepo = notasRepo;
    }

    public Notas lancarNotas(Notas notas){
        if(notas.getNota() > 6.0 && notas.getNota() <= 10.0){
            notas.setStatusAluno(StatusAluno.APROVADO);
        }else if(notas.getNota() > 4.0 && notas.getNota() < 6.0){
            notas.setStatusAluno(StatusAluno.RECUPERACAO);
        }else {
            notas.setStatusAluno(StatusAluno.REPROVADO);
        }
        return notasRepo.save(notas);
    }

    public Notas alterarNotas(Notas notas, String nomeDisciplina, String semestre){
           Optional<Notas> proc = notasRepo.findByDisciplinaAndSemestre(nomeDisciplina, semestre);
           if(proc.isPresent()){
               notas.setId(proc.get().getId());
           }
           return notasRepo.save(notas);
    }
}
