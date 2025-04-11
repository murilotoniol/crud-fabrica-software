package com.crud.crud.services;

import com.crud.crud.enums.TipoItem;
import com.crud.crud.model.ItemMagico;
import com.crud.crud.model.Personagem;
import com.crud.crud.repositories.ItemMagicoRepository;
import com.crud.crud.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    public ItemMagico createItemMagico(ItemMagico itemMagico) {
        if (itemMagico.getTipoItem() == null) {
            throw new IllegalArgumentException("O tipo do item é obrigatorio");
        }

        int forca = itemMagico.getForca() != null ? itemMagico.getForca() : 0;
        int defesa = itemMagico.getDefesa() != null ? itemMagico.getDefesa() : 0;

        if (forca == 0 && defesa == 0) {
            throw new IllegalArgumentException("Itens magicos não podem ter força e defesa igual a zero");
        }

        if (forca > 10 || defesa > 10) {
            throw new IllegalArgumentException("A soma das forca e defesa devem ser no maximo 10");
        }

        switch (itemMagico.getTipoItem()) {
            case ARMA -> itemMagico.setDefesa(0);
            case ARMADURA -> itemMagico.setForca(0);
            case AMULETO -> {
                if (itemMagico.getPersonagem() != null) {
                    Long personagemId = itemMagico.getPersonagem().getId();
                    Personagem personagem = personagemRepository.findById(personagemId)
                            .orElseThrow(() -> new IllegalArgumentException("O personagem não existe"));

                    boolean jaPossuiAmuleto = personagem.getItensMagicos().stream()
                            .anyMatch(i -> i.getTipoItem() == TipoItem.AMULETO);

                    if (jaPossuiAmuleto) {
                        throw new IllegalArgumentException("O personagem ja tem um amuleto vinculado");
                    }
                }
            }
        }

        return itemMagicoRepository.save(itemMagico);
    }

    public List<ItemMagico> getAllItensMagicos() {
        return itemMagicoRepository.findAll();
    }

    public ItemMagico getItemMagicoById(Long id) {
        return itemMagicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item mágico não existe"));
    }
}
