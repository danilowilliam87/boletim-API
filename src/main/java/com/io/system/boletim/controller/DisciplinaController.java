package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Disciplina;
import com.io.system.boletim.service.DisciplinaServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaController {

    private final DisciplinaServices services;

    public DisciplinaController(DisciplinaServices services) {
        this.services = services;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Disciplina> save(@RequestBody Disciplina disciplina){
        Disciplina d = services.save(disciplina);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(disciplina.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .build();
    }
}
