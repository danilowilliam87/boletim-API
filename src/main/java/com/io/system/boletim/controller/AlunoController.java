package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Aluno;
import com.io.system.boletim.service.AlunoServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    private final AlunoServices services;

    public AlunoController(AlunoServices services) {
        this.services = services;
    }


    @RequestMapping(method = RequestMethod.GET, value ="/ola")
    public String helloWorld(){
        return "Hello world";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno){
       Aluno aluno1 = services.save(aluno);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(aluno1.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable(name = "id") Long id){
        Aluno busca = services.find(id);
        return ResponseEntity.ok(busca);
    }
}
