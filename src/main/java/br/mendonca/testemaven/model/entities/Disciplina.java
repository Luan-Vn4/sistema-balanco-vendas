package br.mendonca.testemaven.model.entities;

import java.util.UUID;

public class Disciplina {
    private UUID uuid;
    private String nome;
    private int cargaHoraria;
    private boolean isAtiva;

    public Disciplina() {}

    public Disciplina(String nome, int cargaHoraria, boolean isAtiva) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.isAtiva = isAtiva;
    }

    public UUID getUuid() { return uuid; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public boolean getIsAtiva() { return isAtiva; }
    public void setIsAtiva(boolean isAtiva) { this.isAtiva = isAtiva; }
}
