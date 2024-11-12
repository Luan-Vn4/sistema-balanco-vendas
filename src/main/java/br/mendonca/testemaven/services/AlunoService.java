package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.dao.AlunoDAO;
import br.mendonca.testemaven.model.entities.Aluno;

public class AlunoService {

    public void register(String nome, double media,boolean deletado, boolean isAtivo) throws ClassNotFoundException, SQLException {
        AlunoDAO dao = new AlunoDAO();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMedia(media);
        aluno.setDeletado(deletado);
        aluno.setAtivo(isAtivo);

        dao.register(aluno);
    }

    public List<Aluno> listAllAlunos() throws ClassNotFoundException, SQLException {
        AlunoDAO dao = new AlunoDAO();
        List<Aluno> lista = dao.listAllAlunos();

        return lista;
    }

    public Aluno searchByNome(String nome) throws ClassNotFoundException, SQLException {
        AlunoDAO dao = new AlunoDAO();
        Aluno aluno = dao.searchByNome(nome);

        return aluno != null ? aluno : null;
    }

    public Aluno searchById(UUID uuid) throws ClassNotFoundException, SQLException {
        AlunoDAO dao = new AlunoDAO();
        Aluno aluno = dao.searchById(uuid);

        return aluno != null ? aluno : null;
    }

    public List<Aluno> listAlunosPaginated(int pageNumber) throws ClassNotFoundException, SQLException {
        AlunoDAO dao = new AlunoDAO();
        int pageSize = 3;
        return dao.listAlunosPaginated(pageNumber, pageSize);
    }

    public int getTotalPages() throws ClassNotFoundException, SQLException {
        AlunoDAO dao = new AlunoDAO();
        int totalAlunos = dao.countAllAlunos();
        int pageSize = 3;
        return (int) Math.ceil((double) totalAlunos / pageSize);
    }


}
