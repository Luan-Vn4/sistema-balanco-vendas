package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Professor;
import br.mendonca.testemaven.utils.PageRequest;
import br.mendonca.testemaven.utils.PagedResult;

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
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM professores");

        ArrayList<Professor> lista = unwrapResultSet(rs);

        rs.close();
        return lista;
    }

    private ArrayList<Professor> unwrapResultSet(ResultSet rs) throws SQLException {
        ArrayList<Professor> lista = new ArrayList<>();

        while (rs.next()) {
            Professor professor = new Professor();
            professor.setUuid((UUID) rs.getObject("uuid"));
            professor.setNome(rs.getString("nome"));
            professor.setSalario(rs.getDouble("salario"));
            professor.setAtivo(rs.getBoolean("ativo"));

            lista.add(professor);
        }

        return lista;
    }

    public PagedResult<Professor> findAll(PageRequest pageRequest) throws ClassNotFoundException, SQLException {
        if (pageRequest.getSize() < 0 || pageRequest.getPage() < 0) {
            throw new IllegalArgumentException("O tamanho e número da página devem ser positivos");
        }

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        int totalElements = countAll();
        int totalPages = (int) Math.ceil(totalElements / (double) pageRequest.getSize());

        PreparedStatement st = conn.prepareStatement("SELECT * FROM professores LIMIT ? OFFSET ?");
        st.setInt(1, pageRequest.getSize());
        st.setInt(2, pageRequest.getSize() * pageRequest.getPage());
        ResultSet rs = st.executeQuery();

        ArrayList<Professor> lista = unwrapResultSet(rs);

        var result = new PagedResult<>(lista, pageRequest.getPage(), totalPages, totalElements,
                                       pageRequest.getSize());

        System.out.println(lista);

        rs.close();
        return result;
    }

    public int countAll() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM professores");

        rs.next();
        int count = rs.getInt(1);

        st.close();
        rs.close();
        return count;
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
