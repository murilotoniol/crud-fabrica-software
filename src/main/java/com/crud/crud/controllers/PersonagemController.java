package com.crud.crud.controllers;

import com.crud.crud.entities.Personagem;
import com.crud.crud.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping("/create-personagem")
    public ResponseEntity<Personagem> createPersonagem(@RequestBody Personagem personagem){
        return personagemService.createPersonagem(personagem);
    }
}
