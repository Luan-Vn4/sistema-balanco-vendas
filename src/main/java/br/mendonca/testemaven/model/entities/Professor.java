package br.mendonca.testemaven.model.entities;

import java.util.StringJoiner;
import java.util.UUID;

public class Professor {

    private UUID uuid;

    private String nome;

    private double salario;

    private boolean ativo;

    private boolean deleted;

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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Professor.class.getSimpleName() + "[", "]")
            .add("uuid=" + uuid)
            .add("nome='" + nome + "'")
            .add("salario=" + salario)
            .add("ativo=" + ativo)
            .toString();
    }
}
