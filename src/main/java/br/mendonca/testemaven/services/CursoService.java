package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.CursoDAO;
import br.mendonca.testemaven.model.entities.Curso;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CursoService {

    private static final CursoDAO dao = new CursoDAO();

    public void register(Curso curso) throws SQLException, ClassNotFoundException {
        dao.register(curso);
    }

    public void update(Curso curso) throws SQLException, ClassNotFoundException {
        dao.update(curso);
    }

    public List<Curso> listAllCurso() throws SQLException, ClassNotFoundException {
        return dao.listAllCursos();
    }

    public void deletar(UUID uuid) throws SQLException, ClassNotFoundException {
        dao.deletar(uuid);
    }
}
