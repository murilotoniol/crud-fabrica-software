package com.crud.crud.services;

import com.crud.crud.entities.ItemMagico;
import com.crud.crud.repositories.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public ResponseEntity<ItemMagico> createItemMagico(@RequestBody ItemMagico itemMagico){
        ItemMagico newItemMagico = (ItemMagico) itemMagicoRepository.save(itemMagico);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItemMagico);
    }
}
