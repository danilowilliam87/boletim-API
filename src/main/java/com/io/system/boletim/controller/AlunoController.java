package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Aluno;
import com.io.system.boletim.service.AlunoServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    private final AlunoServices services;

    public AlunoController(AlunoServices services) {
        this.services = services;
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Aluno>findByEmail(@RequestParam(name = "email") String email){
        Aluno busca = services.findByEmail(email);
        return ResponseEntity.ok(busca);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Void> updatePatch(@RequestBody Aluno aluno, @PathVariable Long id){
        Aluno a = services.updatePatch(aluno, id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Void> updatePut(@RequestBody Aluno aluno, @PathVariable Long id){
        Aluno a = services.updatePut(aluno, id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public ResponseEntity<List<Aluno>> findListAll(){
        List<Aluno>list = services.findAll();
        return ResponseEntity
                .ok()
                .body(list);
    }



    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        services.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
