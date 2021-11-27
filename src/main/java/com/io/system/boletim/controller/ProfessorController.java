package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Disciplina;
import com.io.system.boletim.domain.Professor;
import com.io.system.boletim.service.ProfessorServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {

    private final ProfessorServices  services;

    public ProfessorController(ProfessorServices services) {
        this.services = services;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Professor> save(@RequestBody Professor professor){
        professor = services.save(professor);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/id")
                .buildAndExpand(professor.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Long id){
        Professor professor = services.find(id);
        return ResponseEntity.ok(professor);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Professor> findById(@RequestParam String email){
        Professor professor = services.findByEmail(email);
        return ResponseEntity.ok(professor);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Void> updatePatch(@RequestBody Professor professor,
                                            @PathVariable Long id){
        professor = services.updatePatch(professor, id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Void> updatePut(@RequestBody Professor professor,
                                          @PathVariable Long id){
        professor = services.updatePut(professor, id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<Professor>> findAll(){
        List<Professor>list = services.findAll();
        return ResponseEntity
                .ok()
                .body(list);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
         Professor professor = services.find(id);
         services.delete(id);
         return ResponseEntity
                 .noContent()
                 .build();
    }

}
