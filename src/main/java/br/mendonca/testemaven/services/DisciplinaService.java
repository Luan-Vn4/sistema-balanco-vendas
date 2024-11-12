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

    public List<Disciplina> listAllDeletedDisciplinas() throws ClassNotFoundException, SQLException {
        return dao.listAllDeletedDisciplinas();
    }

    public Disciplina listDisciplinaByUuid(UUID uuid) throws ClassNotFoundException, SQLException {
        return dao.searchByUuid(uuid);
    }

    public boolean deletar(UUID uuid) throws ClassNotFoundException, SQLException {
        return dao.deleteByUuid(uuid);
    }

    public List<Disciplina> getDisciplinasPaginated(int page, int pageSize) throws ClassNotFoundException, SQLException {
        return dao.getDisciplinasPaginated(page, pageSize);
    }

    public List<Disciplina> getDeletedDisciplinasPaginated(int page, int pageSize) throws ClassNotFoundException, SQLException {
        return dao.getDeletedDisciplinasPaginated(page, pageSize);
    }

    public int countDeletedDisciplinas() throws ClassNotFoundException, SQLException {
        return dao.countDeletedDisciplinas();
    }

    public int countDisciplinas() throws ClassNotFoundException, SQLException {
        return dao.countDisciplinas();
    }

}
