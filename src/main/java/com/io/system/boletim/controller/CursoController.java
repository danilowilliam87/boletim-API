package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Curso;
import com.io.system.boletim.service.CursoServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

    private final CursoServices services;

    public CursoController(CursoServices services) {
        this.services = services;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Curso>save(@RequestBody Curso curso){
        curso = services.save(curso);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/id")
                .buildAndExpand(curso.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Curso> findById(@PathVariable(value = "id") Long id){
        Curso busca = services.find(id);
        return ResponseEntity
                .ok(busca);
    }
}
