package com.io.system.boletim.service;

import com.io.system.boletim.domain.Aluno;
import com.io.system.boletim.repository.AlunoRepo;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServices {

    private AlunoRepo alunoRepo;

    public AlunoServices(AlunoRepo alunoRepo) {
        this.alunoRepo = alunoRepo;
    }

    //salvar aluno
    public Aluno save(Aluno aluno){
        return alunoRepo.save(aluno);
    }

    //busca aluno pelo id
    public Aluno find(Long id){
        Optional<Aluno> aln = alunoRepo.findById(id);
        return aln.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id :" + id,"Tipo :"
                + Aluno.class.getName()));
    }

    //busca aluno pelo email
    public Aluno findByEmail(String email){
        Optional<Aluno> aln = alunoRepo.findAlunoByEmail(email);
        return aln.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Email :" + email,"Tipo :"
                + Aluno.class.getName()));
    }

    //atualização parcial
    public Aluno updatePatch(Aluno aluno, Long id){
        Optional<Aluno> proc = alunoRepo.findById(id);
        Aluno al = null;
        if(proc.isPresent()) {
            al = proc.get();
            al.setNome(Optional.ofNullable(aluno.getNome()).orElse(al.getNome()));
            al.setEmail(Optional.ofNullable(aluno.getEmail()).orElse(al.getEmail()));
        }
        return save(al);
    }

    //atualização total
    public Aluno updatePut(Aluno aluno, Long id){
        Optional<Aluno> proc = alunoRepo.findById(id);
        if(proc.isPresent()){
           aluno.setId(id);
        }
        return alunoRepo.save(aluno);
    }

    //listagem de alunos
    public List<Aluno> findAll(){
        return alunoRepo.findAll();
    }
    //deletar aluno
    public void delete(Long id){
        alunoRepo.deleteById(id);
    }


}
