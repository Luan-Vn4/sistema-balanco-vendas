package br.mendonca.testemaven.services.professor;

import br.mendonca.testemaven.dao.ProfessorDAO;
import br.mendonca.testemaven.model.entities.Professor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfessorService {

    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public void create(Professor professor) throws SQLException, ClassNotFoundException {
        professorDAO.save(professor);
    }

    public List<Professor> getAll() throws SQLException, ClassNotFoundException {
        return professorDAO.findAll();
    }

    public Optional<Professor> getByUuid(UUID uuid) throws SQLException, ClassNotFoundException {
        return professorDAO.findByUID(uuid);
    }

}
