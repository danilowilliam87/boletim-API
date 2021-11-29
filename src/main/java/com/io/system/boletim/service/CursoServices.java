package com.io.system.boletim.service;

import com.io.system.boletim.domain.Curso;
import com.io.system.boletim.repository.CursoRepo;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServices {

    private final CursoRepo cursoRepo;


    public CursoServices(CursoRepo cursoRepo) {
        this.cursoRepo = cursoRepo;
    }

    public Curso save(Curso curso){
        return cursoRepo.save(curso);
    }

    public Curso find(Long id){
        Optional<Curso> curso = cursoRepo.findById(id);
        return curso.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id :" + id,"Tipo :"
                + Curso.class.getName()));
    }

    public Curso findByName(String name){
        Optional<Curso> curso = cursoRepo.findCursoByNomeEquals(name);
        return curso.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Nome :" + name,"Tipo :"
                + Curso.class.getName()));
    }


    private List<Curso> listar(){
        return cursoRepo.findAll();
    }

    private void delete(Long id){
        cursoRepo.deleteById(id);
    }


}
