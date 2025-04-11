package com.crud.crud.controllers;

import com.crud.crud.model.ItemMagico;
import com.crud.crud.services.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-magicos")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    @PostMapping("/itens-magicos")
    public ResponseEntity<?> createItem(@RequestBody ItemMagico itemMagico) {
        try {
            ItemMagico novoItem = itemMagicoService.createItemMagico(itemMagico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list-all-itens-magicos")
    public ResponseEntity<List<ItemMagico>> getAllItens() {
        return ResponseEntity.ok(itemMagicoService.getAllItensMagicos());
    }

    @GetMapping("/get-item-magico-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(itemMagicoService.getItemMagicoById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}