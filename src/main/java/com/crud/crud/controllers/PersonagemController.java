package com.crud.crud.controllers;

import com.crud.crud.model.ItemMagico;
import com.crud.crud.model.Personagem;
import com.crud.crud.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping("/create-personagem")
    public Personagem createPersonagem(@RequestBody Personagem personagem){
        return personagemService.createPersonagem(personagem);
    }

    @GetMapping("/list-all-personagens")
    public ResponseEntity<List<Personagem>> getAllPersonagens(){
        return (ResponseEntity<List<Personagem>>) personagemService.getAllPersonagens();
    }

    @GetMapping("/list-personagem-by-id/{id}")
    public Personagem getPersonagemById(@PathVariable Long id){
        return personagemService.getPersonagemById(id);
    }

    @DeleteMapping("/delete-personagem/{id}")
    public ResponseEntity<?> deletePersonagem(@PathVariable Long id){
        return personagemService.deletePersonagemById(id);
    }

    @PostMapping("/add-item-magico-personagem/{id}")
    public Personagem adicionarItemMagico(@PathVariable Long id, @RequestBody ItemMagico item) {
        return personagemService.addItemMagico(id, item);
    }

    @PutMapping("/update-nome-aventureiro-personagem/{id}")
    public Personagem atualizarNomeAventureiro(@PathVariable Long id, @RequestBody String novoNomeAventureiro) {
        return personagemService.updateNomeAventureiro(id, novoNomeAventureiro);
    }

    @GetMapping("/list-itens-magicos-personagem/{id}")
    public ResponseEntity<List<ItemMagico>> listarItensMagicos(@PathVariable Long id) {
        return (ResponseEntity<List<ItemMagico>>) personagemService.listItensMagicosPersonagem(id);
    }

    @DeleteMapping("/delete-item-magico-personagem/{itemId}/{id}")
    public Personagem removeItemMagicoPersonagem(@PathVariable Long id, @PathVariable Long itemId) {
        return personagemService.removeItemMagico(id, itemId);
    }

    @GetMapping("/get-amuleto-personagem/{id}")
    public ItemMagico getAmuletoPersonagem(@PathVariable Long id) {
        return personagemService.getAmuletoPersonagem(id);
    }
}
