package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Disciplina;
import com.io.system.boletim.service.DisciplinaServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Disciplina> find(@PathVariable Long id){
        Disciplina d = services.find(id);
        return ResponseEntity.ok(d);
    }
}
