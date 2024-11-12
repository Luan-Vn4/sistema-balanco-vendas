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

    public void register(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO disciplinas (nome, carga_horaria, is_ativo, visualizacao) VALUES (?, ?, ?, ?)");
        ps.setString(1, disciplina.getNome());
        ps.setInt(2, disciplina.getCargaHoraria());
        ps.setBoolean(3, disciplina.getIsAtiva());
        ps.setBoolean(4, true);
        ps.executeUpdate();
    }

    public List<Disciplina> listAllDisciplinas() throws ClassNotFoundException, SQLException {
        List<Disciplina> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM disciplinas WHERE visualizacao = true");

        while (rs.next()) {
            Disciplina disciplina = new Disciplina();
            disciplina.setUuid((UUID) rs.getObject("uuid"));
            disciplina.setNome(rs.getString("nome"));
            disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
            disciplina.setIsAtiva(rs.getBoolean("is_ativo"));
            disciplina.setVisualizacao(rs.getBoolean("visualizacao"));
            lista.add(disciplina);
        }
        return lista;
    }

    public List<Disciplina> listAllDeletedDisciplinas() throws ClassNotFoundException, SQLException {
        List<Disciplina> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM disciplinas WHERE visualizacao = false");

        while (rs.next()) {
            Disciplina disciplina = new Disciplina();
            disciplina.setUuid((UUID) rs.getObject("uuid"));
            disciplina.setNome(rs.getString("nome"));
            disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
            disciplina.setIsAtiva(rs.getBoolean("is_ativo"));
            disciplina.setVisualizacao(rs.getBoolean("visualizacao"));
            lista.add(disciplina);
        }
        return lista;
    }

    public Disciplina searchByUuid(UUID uuid) throws ClassNotFoundException, SQLException {
        Disciplina disciplina = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM disciplinas WHERE uuid = ? AND visualizacao = true");
        ps.setObject(1, uuid);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            disciplina = new Disciplina();
            disciplina.setUuid((UUID) rs.getObject("uuid"));
            disciplina.setNome(rs.getString("nome"));
            disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
            disciplina.setIsAtiva(rs.getBoolean("is_ativo"));
            disciplina.setVisualizacao(rs.getBoolean("visualizacao"));
        }

        return disciplina;
    }

    public boolean deleteByUuid(UUID uuid) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("UPDATE disciplinas SET visualizacao = false WHERE uuid = ?");
        ps.setObject(1, uuid);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Disciplina com UUID " + uuid + " foi marcada como invisível.");
        } else {
            System.out.println("Nenhuma disciplina encontrada com UUID " + uuid);
        }
        return rowsAffected > 0;
    }

    public List<Disciplina> getDisciplinasPaginated(int page, int pageSize) throws ClassNotFoundException, SQLException {
        int offset = (page - 1) * pageSize;
        List<Disciplina> disciplinas = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "SELECT * FROM disciplinas WHERE visualizacao = true LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setUuid(rs.getObject("uuid", UUID.class));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                disciplina.setIsAtiva(rs.getBoolean("is_ativo"));
                disciplina.setVisualizacao(rs.getBoolean("visualizacao"));

                disciplinas.add(disciplina);
            }
        }
        return disciplinas;
    }

    public List<Disciplina> getDeletedDisciplinasPaginated(int page, int pageSize) throws ClassNotFoundException, SQLException {
        int offset = (page - 1) * pageSize;
        List<Disciplina> disciplinas = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "SELECT * FROM disciplinas WHERE visualizacao = false LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setUuid(rs.getObject("uuid", UUID.class));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                disciplina.setIsAtiva(rs.getBoolean("is_ativo"));
                disciplina.setVisualizacao(rs.getBoolean("visualizacao"));

                disciplinas.add(disciplina);
            }
        }
        return disciplinas;
    }

    public int countDisciplinas() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "SELECT COUNT(*) FROM disciplinas WHERE visualizacao = true";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int countDeletedDisciplinas() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "SELECT COUNT(*) FROM disciplinas WHERE visualizacao = false";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

}
