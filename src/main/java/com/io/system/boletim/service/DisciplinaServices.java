package com.io.system.boletim.service;

import com.io.system.boletim.domain.Aluno;
import com.io.system.boletim.domain.Disciplina;
import com.io.system.boletim.repository.AlunoRepo;
import com.io.system.boletim.repository.DisciplinaRepo;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaServices {

    private final DisciplinaRepo disciplinaRepo;

    public DisciplinaServices(DisciplinaRepo disciplinaRepo) {
        this.disciplinaRepo = disciplinaRepo;
    }

    //salvar aluno
    public Disciplina save(Disciplina disciplina){
        return disciplinaRepo.save(disciplina);
    }

    //busca aluno pelo id
    public Disciplina find(Long id){
        Optional<Disciplina> disc = disciplinaRepo.findById(id);
        return disc.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id :" + id,"Tipo :"
                + Aluno.class.getName()));
    }



    //atualização total
    public Disciplina updatePut(Disciplina disciplina, Long id){
        Optional<Disciplina> proc = disciplinaRepo.findById(id);
        if(proc.isPresent()){
          disciplina.setId(id);
        }
        return disciplinaRepo.save(disciplina);
    }

    //listagem de alunos
    public List<Disciplina> findAll(){
        return disciplinaRepo.findAll();
    }
    //deletar aluno
    public void delete(Long id){
        disciplinaRepo.deleteById(id);
    }


}
