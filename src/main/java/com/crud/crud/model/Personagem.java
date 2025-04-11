package com.crud.crud.model;

import com.crud.crud.enums.Classe;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String nomeAventureiro;
    @Enumerated(EnumType.STRING)
    private Classe classe;
    private Integer level;
    @OneToMany
    private List<ItemMagico> itensMagicos;
    private Integer forca;
    private Integer defesa;

    public Personagem(){}

    public Personagem(String nome, String nomeAventureiro, Classe classe, Integer level, Integer forca, Integer defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

    @Transient
    public int getForcaTotal() {
        int forcaItens = itensMagicos != null ? itensMagicos.stream().mapToInt(i -> i.getForca() != null ? i.getForca() : 0).sum() : 0;
        return (forca != null ? forca : 0) + forcaItens;
    }

    @Transient
    public int getDefesaTotal() {
        int defesaItens = itensMagicos != null ? itensMagicos.stream().mapToInt(i -> i.getDefesa() != null ? i.getDefesa() : 0).sum() : 0;
        return (defesa != null ? defesa : 0) + defesaItens;
    }
}
