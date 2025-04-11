package com.crud.crud.services;

import com.crud.crud.enums.TipoItem;
import com.crud.crud.model.ItemMagico;
import com.crud.crud.model.Personagem;
import com.crud.crud.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public Personagem createPersonagem(Personagem personagem) {
        int totalPontos = personagem.getForca() + personagem.getDefesa();
        if (totalPontos > 10) {
            throw new IllegalArgumentException("A soma de força e defesa não pode ser maior que 10");
        }
        return personagemRepository.save(personagem);
    }

    public List<Personagem> getAllPersonagens() {
        return personagemRepository.findAll();
    }

    public Personagem getPersonagemById(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("O personagem não existe"));
    }

    public ResponseEntity<?> deletePersonagemById(Long id) {
        if (!personagemRepository.existsById(id)) {
            throw new IllegalArgumentException("O personagem não existe");
        }
        personagemRepository.deleteById(id);
        return null;
    }

    public Personagem addItemMagico(Long personagemId, ItemMagico itemMagico) {
        Personagem personagem = getPersonagemById(personagemId);

        if (itemMagico.getTipoItem() == TipoItem.AMULETO) {
            boolean jaPossuiAmuleto = personagem.getItensMagicos().stream()
                    .anyMatch(item -> item.getTipoItem() == TipoItem.AMULETO);
            if (jaPossuiAmuleto) {
                throw new IllegalArgumentException("O personagem ja tem um amuleto vinculado");
            }
        }

        itemMagico.setPersonagem(personagem);
        personagem.getItensMagicos().add(itemMagico);
        return personagemRepository.save(personagem);
    }

    public Personagem removeItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = getPersonagemById(personagemId);
        boolean removed = personagem.getItensMagicos().removeIf(item -> item.getId().equals(itemId));

        if (!removed) {
            throw new IllegalArgumentException("O item mágico não existe para esse personagem");
        }

        return personagemRepository.save(personagem);
    }

    public Personagem updateNomeAventureiro(Long id, String novoNomeAventureiro) {
        Personagem personagem = getPersonagemById(id);
        personagem.setNomeAventureiro(novoNomeAventureiro);
        return personagemRepository.save(personagem);
    }

    public List<ItemMagico> listItensMagicosPersonagem(Long personagemId) {
        return getPersonagemById(personagemId).getItensMagicos();
    }

    public ItemMagico getAmuletoPersonagem(Long personagemId) {
        return getPersonagemById(personagemId).getItensMagicos().stream()
                .filter(item -> item.getTipoItem() == TipoItem.AMULETO)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("O personagem não tem amuleto"));
    }
}