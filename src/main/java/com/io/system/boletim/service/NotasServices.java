package com.io.system.boletim.service;

import com.io.system.boletim.Enums.StatusAluno;
import com.io.system.boletim.domain.Aluno;
import com.io.system.boletim.domain.Disciplina;
import com.io.system.boletim.domain.Notas;
import com.io.system.boletim.repository.AlunoRepo;
import com.io.system.boletim.repository.DisciplinaRepo;
import com.io.system.boletim.repository.NotasRepo;
import org.aspectj.weaver.ast.Not;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotasServices {

    private final NotasRepo notasRepo;
    private final DisciplinaRepo disciplinaRepo;
    private final AlunoRepo alunoRepo;

    public NotasServices(NotasRepo notasRepo, DisciplinaRepo disciplinaRepo, AlunoRepo alunoRepo) {
        this.notasRepo = notasRepo;
        this.disciplinaRepo = disciplinaRepo;
        this.alunoRepo = alunoRepo;
    }

    public Notas find(Long id){
        Optional<Notas> notas = notasRepo.findById(id);
        return notas.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id :" + id,"Tipo :"
                + Notas.class.getName()));
    }

    public Notas findByAlunoAndDisciplina(String emailAluno, String nomeDisciplina){
        Notas n = new Notas();
        Optional<Notas> buscaNota = null;
        Optional<Aluno> buscaAluno = alunoRepo.findAlunoByEmail(emailAluno);
        Optional<Disciplina> buscaDisciplina = disciplinaRepo.findDisciplinaByNomeEquals(nomeDisciplina);
        if(buscaAluno.isPresent() && buscaDisciplina.isPresent()){
            buscaNota = notasRepo
                    .findNotasByDisciplinaAndAluno(buscaDisciplina.get(), buscaAluno.get());
            n = buscaNota.get();
        }
        Notas finalN = n;
        return buscaNota.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id :",
              finalN.getId() + "Tipo :"
                + Notas.class.getName()));
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


}
