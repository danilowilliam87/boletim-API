package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Professor;
import com.io.system.boletim.service.ProfessorServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}
