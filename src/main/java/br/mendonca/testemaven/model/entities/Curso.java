package br.mendonca.testemaven.model.entities;

import java.util.StringJoiner;
import java.util.UUID;

public class Curso {
    private UUID uuid;
    private String nome;
    private double mediaMec;
    private boolean isAtivo;
    private boolean deleted;
//    private UUID userUuid;



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

    public boolean getAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    //    public UUID getUserUuid() {
//        return userUuid;
//    }
//
//    public void setUserUuid(UUID userUuid) {
//        this.userUuid = userUuid;
//    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Curso.class.getSimpleName() + "[", "]")
                .add("uuid=" + uuid)
                .add("nome='" + nome + "'")
                .add("mediaMec=" + mediaMec)
                .add("isAtivo=" + isAtivo)
                .toString();
    }
}
