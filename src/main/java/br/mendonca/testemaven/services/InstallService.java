package br.mendonca.testemaven.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.mendonca.testemaven.dao.ConnectionPostgres;

public class InstallService {
	
	private void statement(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);
		
		Statement st = conn.createStatement();
		st.executeUpdate(sql);
		st.close();
	}
	
	public void testConnection() throws ClassNotFoundException, SQLException {
		ConnectionPostgres.getConexao();
	}
	
	public void deleteUserTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS users");
	}
	
	public void createUserTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE users ("
					+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
					+ "    name VARCHAR(255) NOT NULL,"
					+ "    email VARCHAR(255) NOT NULL,"
					+ "    password VARCHAR(255) NOT NULL)");
	}
    public void deleteDisciplinaTable() throws ClassNotFoundException, SQLException {
        statement("DROP TABLE IF EXISTS disciplinas");
    }

    public void createDisciplinaTable() throws ClassNotFoundException, SQLException {
        statement("CREATE TABLE disciplinas ("
                + "uuid UUID DEFAULT gen_random_uuid() NOT NULL,"
                + "nome VARCHAR(255) NOT NULL,"
                + "carga_horaria INT NOT NULL,"
                + "is_ativo BOOLEAN NOT NULL)");
    }

    public void deleteAlunoTable() throws ClassNotFoundException, SQLException {
        statement("DROP TABLE IF EXISTS alunos");
    }

    public void createAlunoTable() throws ClassNotFoundException, SQLException {
        statement("CREATE TABLE alunos ("
                + " uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + " nome VARCHAR(255) NOT NULL,"
                + " media DECIMAL NOT NULL,"
                + "isAtivo BOOLEAN NOT NULL)");
    }

    public void deleteCursoTable() throws ClassNotFoundException, SQLException {
        statement("DROP TABLE IF EXISTS cursos");
    }

    public void createCursoTable() throws ClassNotFoundException, SQLException {
        statement("CREATE TABLE cursos ("
                + "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + "    nome VARCHAR(255) NOT NULL,"
                + "    media_mec DOUBLE PRECISION,"
                + "    is_ativo BOOLEAN,"
                + "    user_uuid UUID,"
                + "    FOREIGN KEY (user_uuid) REFERENCES users(uuid)"
                + ")");
    }
}
