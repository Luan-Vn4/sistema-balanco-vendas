package br.mendonca.testemaven.services.professor;

import br.mendonca.testemaven.dao.ProfessorDAO;
import br.mendonca.testemaven.model.entities.Professor;
import br.mendonca.testemaven.utils.PageRequest;
import br.mendonca.testemaven.utils.PagedResult;

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

    /**
     * @throws IllegalArgumentException caso o tamanho da página ou o número da página sejam negativos
     */
    public PagedResult<Professor> getAll(PageRequest pageRequest) throws SQLException, ClassNotFoundException {
        if (pageRequest.getSize() < 0 || pageRequest.getPage() < 0) {
            throw new IllegalArgumentException("O tamanho e número da página devem ser positivos");
        }

        return professorDAO.findAll(pageRequest);
    }

    public Optional<Professor> getByUuid(UUID uuid) throws SQLException, ClassNotFoundException {
        return professorDAO.findByUID(uuid);
    }

}
