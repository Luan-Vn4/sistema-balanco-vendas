package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfessorDAO {

    public void save(Professor professor) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO professores (nome, salario, ativo) values (?,?,?)");
        ps.setString(1, professor.getNome());
        ps.setDouble(2, professor.getSalario());
        ps.setBoolean(3, professor.isAtivo());
        ps.executeUpdate();
        ps.close();
    }

    public List<Professor> findAll() throws ClassNotFoundException, SQLException {
        ArrayList<Professor> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM professores");

        while (rs.next()) {
            Professor professor = new Professor();
            professor.setUuid((UUID) rs.getObject("uuid"));
            professor.setNome(rs.getString("nome"));
            professor.setSalario(rs.getDouble("salario"));
            professor.setAtivo(rs.getBoolean("ativo"));

            lista.add(professor);
        }

        rs.close();

        return lista;
    }

    public Optional<Professor> findByUID(UUID uuid) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM professores WHERE uuid = ?");
        ps.setObject(1, uuid);
        System.out.println(ps);

        Professor professor = null;
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            professor = new Professor();
            professor.setUuid((UUID) rs.getObject("uuid"));
            professor.setNome(rs.getString("nome"));
            professor.setSalario(rs.getDouble("salario"));
            professor.setAtivo(rs.getBoolean("ativo"));
        }

        rs.close();

        return Optional.ofNullable(professor);
    }

}
