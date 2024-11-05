package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.DisciplinaDAO;
import br.mendonca.testemaven.model.entities.Disciplina;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DisciplinaService {
    private static final DisciplinaDAO dao = new DisciplinaDAO();

    public void register(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        dao.register(disciplina);
    }

    public List<Disciplina> listAllDisciplinas() throws ClassNotFoundException, SQLException {
        return dao.listAllDisciplinas();
    }

    public Disciplina listDisciplinaByUuid(UUID uuid) throws ClassNotFoundException, SQLException {
        return dao.searchByUuid(uuid);
    }

    public void deletar(UUID uuid) throws ClassNotFoundException, SQLException {
        dao.deleteByUuid(uuid);
    }
}
