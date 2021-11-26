package com.io.system.boletim.service;

import com.io.system.boletim.domain.Aluno;
import com.io.system.boletim.domain.Professor;
import com.io.system.boletim.repository.AlunoRepo;
import com.io.system.boletim.repository.ProfessorRepo;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServices {

    private final ProfessorRepo professorRepo;

    public ProfessorServices(ProfessorRepo professorRepo) {
        this.professorRepo = professorRepo;
    }

    //salvar professor
    public Professor save(Professor professor){
        return professorRepo.save(professor);
    }

    //busca professor pelo id
    public Professor find(Long id){
        Optional<Professor> aln = professorRepo.findById(id);
        return aln.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id :" + id,"Tipo :"
                + Professor.class.getName()));
    }

    //busca professor pelo email
    public Professor findByEmail(String email){
        Optional<Professor> aln = professorRepo.findProfessorByEmail(email);
        return aln.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Email :" + email,"Tipo :"
                + Professor.class.getName()));
    }

    //atualização parcial
    public Professor updatePatch(Professor professor, Long id){
        Optional<Professor> proc = professorRepo.findById(id);
        Professor prof = null;
        if(proc.isPresent()) {
            prof = proc.get();
            prof.setNome(Optional.ofNullable(professor.getNome()).orElse(prof.getNome()));
            prof.setEmail(Optional.ofNullable(professor.getEmail()).orElse(prof.getEmail()));
            prof.setDisciplinas(Optional.ofNullable(professor.getDisciplinas()).orElse(prof.getDisciplinas()));
        }
        return save(prof);
    }

    //atualização total
    public Professor updatePut(Professor professor, Long id){
        Optional<Professor> proc = professorRepo.findById(id);
        if(proc.isPresent()){
           professor.setId(id);
        }
        return professorRepo.save(professor);
    }

    //listagem de professores
    public List<Professor> findAll(){
        return professorRepo.findAll();
    }
    //deletar aluno
    public void delete(Long id){
        professorRepo.deleteById(id);
    }


}
