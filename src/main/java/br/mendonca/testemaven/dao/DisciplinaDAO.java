package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.model.entities.Disciplina;

public class DisciplinaDAO {

    // Método para inserir uma nova disciplina
    public void register(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO disciplinas (nome, carga_horaria, is_ativo) VALUES (?, ?, ?)");
        ps.setString(1, disciplina.getNome());
        ps.setInt(2, disciplina.getCargaHoraria());
        ps.setBoolean(3, disciplina.getIsAtiva());
        ps.execute();

    }

    // Método para listar todas as disciplinas
    public List<Disciplina> listAllDisciplinas() throws ClassNotFoundException, SQLException {
        ArrayList<Disciplina> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM disciplinas");

        while (rs.next()) {
            Disciplina disciplina = new Disciplina();
            disciplina.setUuid((UUID) rs.getObject("uuid"));
            disciplina.setNome(rs.getString("nome"));
            disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
            disciplina.setIsAtiva(rs.getBoolean("is_ativo"));

            lista.add(disciplina);
        }

        return lista;
    }


    // Método para buscar disciplina por nome exato
    public Disciplina searchByUuid(UUID uuid) throws ClassNotFoundException, SQLException {
        Disciplina disciplina = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM disciplinas WHERE uuid = ?");
        ps.setObject(1, uuid);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            disciplina = new Disciplina();
            disciplina.setUuid((UUID) rs.getObject("uuid"));
            disciplina.setNome(rs.getString("nome"));
            disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
            disciplina.setIsAtiva(rs.getBoolean("is_ativo"));
        }


        return disciplina;
    }

    public void deleteByUuid(UUID uuid) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("DELETE FROM disciplinas WHERE uuid = ?");
        ps.setObject(1, uuid);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Disciplina com UUID " + uuid + " foi deletada com sucesso.");
        } else {
            System.out.println("Nenhuma disciplina encontrada com UUID " + uuid);
        }

    }
}
