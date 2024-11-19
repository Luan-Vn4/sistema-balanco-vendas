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

		statement("DROP TABLE IF EXISTS users CASCADE");

	}

	public void createUserTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE users ("
					+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
					+ "    name VARCHAR(255) NOT NULL,"
					+ "    email VARCHAR(255) NOT NULL,"
					+ "    password VARCHAR(255) NOT NULL)");
	}

    public void deleteDisciplinaTable() throws ClassNotFoundException, SQLException {
        statement("DROP TABLE IF EXISTS disciplinas CASCADE");
    }

    public void createDisciplinaTable() throws ClassNotFoundException, SQLException {
        statement("CREATE TABLE disciplinas ("
                + "uuid UUID DEFAULT gen_random_uuid() NOT NULL,"
                + "nome VARCHAR(255) NOT NULL,"
                + "carga_horaria INT NOT NULL,"
                + "is_ativo BOOLEAN NOT NULL," +
				"visualizacao BOOLEAN NOT NULL," +
				"data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL," +
				"numero_curtidas INT DEFAULT 0)");
    }

	public void populateDisciplinaTable() throws ClassNotFoundException, SQLException {
		statement("INSERT INTO disciplinas (nome, carga_horaria, is_ativo, visualizacao) VALUES "
				+ "('Matematica', 60, true, true), "
				+ "('Fisica', 45, true, true), "
				+ "('Quimica', 50, true, true), "
				+ "('Biologia', 40, true, true), "
				+ "('Historia', 35, true, true), "
				+ "('Geografia', 30, true, true), "
				+ "('Literatura', 55, true, true)"
		);
	}


	public void deleteProfessoresTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS professores CASCADE");
	}

	public void createProfessoresTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE professores ("
			+ "uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
			+ "nome VARCHAR(255) NOT NULL,"
			+ "salario DECIMAL(10,2) NOT NULL,"
			+ "ativo BOOLEAN NOT NULL,"
			+ "deleted BOOLEAN DEFAULT false NOT NULL," +
			"data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL," +
				"numero_curtidas INT DEFAULT 0)");
	}

	public void populateProfessoresTable() throws ClassNotFoundException, SQLException {
		statement("INSERT INTO professores (nome, salario, ativo) VALUES " +
			"('Jonas', 15000.50, false)," +
			"('Maria', 16500.99, true)," +
			"('Eduardo', 2500.35, true)," +
			"('Aécio', 5000.25, false)," +
			"('Marilha', 8350.32, false)," +
			"('João', 3500.50, true)," +
			"('Lucas', 1500.95, false)"
		);
	}

    public void deleteAlunoTable() throws ClassNotFoundException, SQLException {
        statement("DROP TABLE IF EXISTS alunos CASCADE");
    }

    public void createAlunoTable() throws ClassNotFoundException, SQLException {
        statement("CREATE TABLE alunos ("
                + " uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + " nome VARCHAR(255) NOT NULL,"
                + " media DECIMAL NOT NULL,"
                + " deletado BOOLEAN NOT NULL,"
                + " isAtivo BOOLEAN NOT NULL," +
				"data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL," +
				"numero_curtidas INT DEFAULT 0)");
    }

    public void populateAlunosTable() throws ClassNotFoundException, SQLException {
        statement("INSERT INTO alunos (nome, media, deletado, isAtivo) VALUES "
                + "('Alice', 8.5, false, true),"
                + "('Bruno', 7.2, false, false),"
                + "('Carla', 9.0,false, true),"
                + "('Daniel', 5.8, false, false),"
                + "('Elisa', 7.9, false, true),"
                + "('Felipe', 6.3, false, true),"
                + "('Gabriela', 8.7, false, false)");
    }

    public void deleteCursoTable() throws ClassNotFoundException, SQLException {
        statement("DROP TABLE IF EXISTS cursos CASCADE");
    }

    public void createCursoTable() throws ClassNotFoundException, SQLException {
        statement("CREATE TABLE cursos ("
                + "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + "    nome VARCHAR(255) NOT NULL,"
                + "    media_mec DOUBLE PRECISION,"
                + "    is_ativo BOOLEAN,"
				+ "	is_deleted BOOLEAN DEFAULT false NOT NULL," +
				"data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL," +
				"numero_curtidas INT DEFAULT 0)"
		);
    }


	public void populateCursosTable() throws ClassNotFoundException, SQLException {
		statement("INSERT INTO cursos (nome, media_mec, is_ativo, is_deleted) VALUES " +
				"('Psicologia', 4, true, false)," +
				"('Matematica', 3, true, false)," +
				"('Farmacia', 1, false, true)," +
				"('Engenharia de Software', 4, true, false)," +
				"('Pedagogia', 2, false, true)," +
				"('Medicina', 4, false, true)," +
				"('Letras', 3, true, false)"
		);
	}



}
