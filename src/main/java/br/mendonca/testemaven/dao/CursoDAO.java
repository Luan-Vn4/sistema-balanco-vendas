package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Curso;
import br.mendonca.testemaven.utils.PageRequest;
import br.mendonca.testemaven.utils.PagedResult;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CursoDAO {


    public void register(Curso curso) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO cursos (nome, media_mec, is_ativo) values (?, ?, ?)");
        ps.setString(1, curso.getNome());
        ps.setDouble(2, curso.getMediaMec());
        ps.setBoolean(3, curso.getAtivo());


        ps.execute();
        ps.close();
    }

    public List<Curso> findAll() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM cursos");

        ArrayList<Curso> lista = unwrapResultSet(rs);
        rs.close();
        return lista;
    }

    private ArrayList<Curso> unwrapResultSet(ResultSet rs) throws SQLException {
        ArrayList<Curso> lista = new ArrayList<>();
        while (rs.next()) {
            Curso curso = new Curso();
            curso.setUuid((UUID) rs.getObject("uuid"));
            curso.setNome(rs.getString("nome"));
            curso.setMediaMec(rs.getDouble("media_mec"));
            curso.setAtivo(rs.getBoolean("is_ativo"));
            lista.add(curso);
        }
        return lista;
    }

    public PagedResult<Curso> findAll(PageRequest pageRequest) throws ClassNotFoundException, SQLException {
        if (pageRequest.getSize() < 0 || pageRequest.getPage() < 0) {
            throw new IllegalArgumentException("O tamanho e número da página devem ser positivos");
        }
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);
        int totalElements = countAll();
        int totalPages = (int) Math.ceil(totalElements / (double) pageRequest.getSize());
        PreparedStatement st = conn.prepareStatement("SELECT * FROM cursos LIMIT ? OFFSET ?");
        st.setInt(1, pageRequest.getSize());
        st.setInt(2, pageRequest.getSize() * (pageRequest.getPage()));
        ResultSet rs = st.executeQuery();
        ArrayList<Curso> lista = unwrapResultSet(rs);
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
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM cursos");

        rs.next();
        int count = rs.getInt(1);

        st.close();
        rs.close();
        return count;
    }



//    public void update(Curso curso) throws ClassNotFoundException, SQLException {
//        Connection conn = ConnectionPostgres.getConexao();
//        conn.setAutoCommit(true);
//
//        PreparedStatement ps = conn.prepareStatement("UPDATE cursos SET nome = ?, media_mec = ?, is_ativo = ?, user_uuid = ? " +
//                "WHERE uuid = ?");
//        ps.setString(1, curso.getNome());
//        ps.setDouble(2, curso.getMediaMec());
//        ps.setBoolean(3, curso.getAtivo());
//        ps.setObject(4, curso.getUserUuid());
//        ps.setObject(5, curso.getUuid()); // uuid do curso para a cláusula WHERE
//
//        ps.executeUpdate();
//        ps.close();
//    }


    public Optional<Curso> findByUID(UUID uuid) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM cursos WHERE uuid = ?");
        ps.setObject(1, uuid);
        System.out.println(ps);

        Curso curso = null;

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            curso = new Curso();
            curso.setUuid((UUID) rs.getObject("uuid"));
            curso.setNome(rs.getString("nome"));
            curso.setMediaMec(rs.getDouble("media_mec"));
            curso.setAtivo(rs.getBoolean("is_ativo"));
        }

        rs.close();

        return Optional.ofNullable(curso);
    }

//    public Curso searchByNome(String nome) throws ClassNotFoundException, SQLException {
//        Curso curso = null;
//
//        Connection conn = ConnectionPostgres.getConexao();
//        conn.setAutoCommit(true);
//
//        PreparedStatement ps = conn.prepareStatement("SELECT * FROM cursos WHERE nome LIKE ?");
//        ps.setString(1, "%" + nome + "%");
//
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//            curso = new Curso();
//            curso.setUuid(UUID.fromString(rs.getString("uuid")));
//            curso.setNome(rs.getString("nome"));
//            curso.setMediaMec(rs.getDouble("media_mec"));
//            curso.setAtivo(rs.getBoolean("is_ativo"));
//            curso.setUserUuid(UUID.fromString(rs.getString("user_uuid")));
//        }
//
//        rs.close();
//        ps.close();
//
//        return curso;
//    }

//    public void deletar(UUID uuid) throws ClassNotFoundException, SQLException {
//        Connection conn = ConnectionPostgres.getConexao();
//        conn.setAutoCommit(true);
//
//        PreparedStatement statement = conn.prepareStatement("DELETE FROM cursos WHERE uuid = ?");
//        statement.setObject(1, uuid);
//        int deleted = statement.executeUpdate();
//
//        statement.close();
//
//        if (deleted < 1) {
//            throw new RuntimeException("Não foi possível achar um curso com UUID: " + uuid);
//        }
//    }





}
