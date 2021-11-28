package com.io.system.boletim.controller;

import com.io.system.boletim.domain.Notas;
import com.io.system.boletim.service.NotasServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
}
