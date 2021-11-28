package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Notas;
import com.io.system.boletim.service.NotasServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
public class NotasController {

    private final NotasServices services;

    public NotasController(NotasServices services) {
        this.services = services;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Notas> save( @RequestBody Notas notas){
        notas = services.lancarNotas(notas);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/id")
                .buildAndExpand(notas.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Notas> findByAlunoAndDisciplina(@RequestParam(value = "email") String emailAluno,
                                                          @RequestParam(value = "nome") String nomeDiscplina){
        Notas notas = services.findByAlunoAndDisciplina(emailAluno, nomeDiscplina);
        return ResponseEntity
                .ok()
                .body(notas);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<Notas>> findAllByAlunoAndSemestre(@RequestParam(value = "email") String emailAluno,
                                                                 @RequestParam(value = "semestre") String semestre){
        List<Notas> list = services.findAllByAlunoAndSemestre( emailAluno, semestre );
        return ResponseEntity
                .ok()
                .body(list);
    }
}
