package com.crud.crud.services;

import com.crud.crud.entities.Personagem;
import com.crud.crud.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public ResponseEntity<Personagem> createPersonagem(@RequestBody Personagem personagem){
        Personagem newPersonagem = (Personagem) personagemRepository.save(personagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPersonagem);
    }


}
