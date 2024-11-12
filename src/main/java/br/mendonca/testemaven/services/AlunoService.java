package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.dao.AlunoDAO;
import br.mendonca.testemaven.model.entities.Aluno;

public class AlunoService {

    private final AlunoDAO dao = new AlunoDAO();

    public void register(String nome, double media, boolean deletado, boolean isAtivo) throws ClassNotFoundException, SQLException {

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMedia(media);
        aluno.setDeletado(deletado);
        aluno.setAtivo(isAtivo);

        dao.register(aluno);
    }

    public List<Aluno> listAllAlunos() throws ClassNotFoundException, SQLException {
        List<Aluno> lista = dao.listAllAlunos();

        return lista;
    }

    public Aluno searchByNome(String nome) throws ClassNotFoundException, SQLException {
        Aluno aluno = dao.searchByNome(nome);

        return aluno != null ? aluno : null;
    }

    public Aluno searchById(UUID uuid) throws ClassNotFoundException, SQLException {
        Aluno aluno = dao.searchById(uuid);

        return aluno != null ? aluno : null;
    }

    public List<Aluno> listAlunosPaginated(int pageNumber, boolean deletado) throws ClassNotFoundException, SQLException {
        int pageSize = 3;
        return dao.listAlunosPaginated(pageNumber, pageSize, deletado);
    }

    public int getTotalPages(boolean deletado) throws ClassNotFoundException, SQLException {
        int totalAlunos = dao.countAllAlunos(deletado);
        int pageSize = 3;
        int totalPages = (int) Math.ceil((double) totalAlunos / pageSize);
        if (totalPages == 0) {
            return 1;
        }
        return totalPages;
    }

    public void deletar(UUID uuid) throws ClassNotFoundException, SQLException {
        dao.deleteByUuid(uuid);
    }
}
