package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.model.entities.Aluno;

public class AlunoDAO {

    public void register(Aluno aluno) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO alunos (uuid, nome, media, isAtivo) VALUES (?, ?, ?, ?)");
        ps.setObject(1, UUID.randomUUID());
        ps.setString(2, aluno.getNome());
        ps.setDouble(3, aluno.getMedia());
        ps.setBoolean(4, aluno.isAtivo());
        ps.execute();
        ps.close();
    }

    public List<Aluno> listAllAlunos() throws ClassNotFoundException, SQLException {
        List<Aluno> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM alunos");

        while (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setUuid((UUID) rs.getObject("uuid"));
            aluno.setNome(rs.getString("nome"));
            aluno.setMedia(rs.getDouble("media"));
            aluno.setAtivo(rs.getBoolean("isAtivo"));

            lista.add(aluno);
        }

        rs.close();
        st.close();

        return lista;
    }

    public Aluno searchByNome(String nome) throws ClassNotFoundException, SQLException {
        Aluno aluno = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alunos WHERE LOWER(nome) LIKE LOWER(?)");
        ps.setString(1, nome + "%");

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            aluno = new Aluno();
            aluno.setUuid((UUID) rs.getObject("uuid"));
            aluno.setNome(rs.getString("nome"));
            aluno.setMedia(rs.getDouble("media"));
            aluno.setAtivo(rs.getBoolean("isAtivo"));
        }

        rs.close();
        ps.close();

        return aluno;
    }

    public Aluno searchById(UUID uuid) throws ClassNotFoundException, SQLException {
        Aluno aluno = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM alunos WHERE uuid = ?");
        ps.setObject(1, uuid);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            aluno = new Aluno();
            aluno.setUuid((UUID) rs.getObject("uuid"));
            aluno.setNome(rs.getString("nome"));
            aluno.setMedia(rs.getDouble("media"));
            aluno.setAtivo(rs.getBoolean("isAtivo"));
        }

        rs.close();
        ps.close();

        return aluno;
    }
}
