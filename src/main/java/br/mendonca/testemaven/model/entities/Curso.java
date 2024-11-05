package br.mendonca.testemaven.model.entities;

import java.util.UUID;

public class Curso {
    private UUID uuid;
    private String nome;
    private double mediaMec;
    private Boolean isAtivo;
    private UUID userUuid;

    public Curso() {}

    public Curso(UUID uuid, String nome, double mediaMec, Boolean isAtivo, UUID userUuid) {
        this.uuid = uuid;
        this.nome = nome;
        this.mediaMec = mediaMec;
        this.isAtivo = isAtivo;
        this.userUuid = userUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMediaMec() {
        return mediaMec;
    }

    public void setMediaMec(double mediaMec) {
        this.mediaMec = mediaMec;
    }

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void setAtivo(Boolean ativo) {
        isAtivo = ativo;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }
}
