package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.CursoDAO;
import br.mendonca.testemaven.model.entities.Curso;
import br.mendonca.testemaven.utils.PageRequest;
import br.mendonca.testemaven.utils.PagedResult;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CursoService {

    private static final CursoDAO dao = new CursoDAO();

    public void register(Curso curso) throws SQLException, ClassNotFoundException {
        dao.register(curso);
    }

    public List<Curso> getAll() throws SQLException, ClassNotFoundException {
        return dao.findAll();
    }

    /**
     * @throws IllegalArgumentException caso o tamanho da página ou o número da página sejam negativos
     */
    public PagedResult<Curso> getAll(PageRequest pageRequest) throws SQLException, ClassNotFoundException {
        if (pageRequest.getSize() < 0 || pageRequest.getPage() < 0) {
            throw new IllegalArgumentException("O tamanho e número da página devem ser positivos");
        }

        return dao.findAll(pageRequest);
    }

    public Optional<Curso> getByUuid(UUID uuid) throws SQLException, ClassNotFoundException {
        return dao.findByUID(uuid);
    }

    public PagedResult<Curso> getAllDeleted(PageRequest pageRequest) throws SQLException, ClassNotFoundException {
        if (pageRequest.getSize() < 0 || pageRequest.getPage() < 0) {
            throw new IllegalArgumentException("O tamanho e número da página devem ser positivos");
        }

        return dao.findAllDeleted(pageRequest);
    }

    public void delete(UUID uuid) throws SQLException, ClassNotFoundException {
        dao.deletar(uuid);
    }



}
