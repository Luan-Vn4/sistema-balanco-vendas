package br.mendonca.testemaven.services;


import br.mendonca.testemaven.dao.*;
import br.mendonca.testemaven.model.entities.Curso;
import br.mendonca.testemaven.model.entities.Disciplina;
import br.mendonca.testemaven.model.entities.Professor;
import br.mendonca.testemaven.model.entities.Aluno;
import br.mendonca.testemaven.services.dto.TimelineItemDTO;
import br.mendonca.testemaven.services.professor.ProfessorService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class TimelineService {

    private final CursoService cursoRepository = new CursoService();
    private final DisciplinaService disciplinaRepository = new DisciplinaService();
    private final ProfessorService professorRepository = new ProfessorService();
    private final AlunoService alunoRepository = new AlunoService();

    public List<TimelineItemDTO> getTimeline() throws SQLException, ClassNotFoundException {
        List<TimelineItemDTO> timeline = new ArrayList<>();

        for (Curso curso : cursoRepository.getAll()) {
            TimelineItemDTO item = new TimelineItemDTO();
            item.setEntityId(curso.getUuid());
            item.setEntityType("Curso");
            item.setDisplayName(curso.getNome());
            item.setCreationOrder(getCreationTimestampForCurso(curso.getUuid()));
            timeline.add(item);
        }

        for (Disciplina disciplina : disciplinaRepository.listAllDisciplinas()) {
            TimelineItemDTO item = new TimelineItemDTO();
            item.setEntityId(disciplina.getUuid());
            item.setEntityType("Disciplina");
            item.setDisplayName(disciplina.getNome());
            item.setCreationOrder(getCreationTimestampForDisciplina(disciplina.getUuid()));
            timeline.add(item);
        }

        for (Professor professor : professorRepository.getAll()) {
            TimelineItemDTO item = new TimelineItemDTO();
            item.setEntityId(professor.getUuid());
            item.setEntityType("Professor");
            item.setDisplayName(professor.getNome());
            item.setCreationOrder(getCreationTimestampForProfessor(professor.getUuid()));
            timeline.add(item);
        }

        for (Aluno aluno : alunoRepository.listAllAlunos()) {
            TimelineItemDTO item = new TimelineItemDTO();
            item.setEntityId(aluno.getUuid());
            item.setEntityType("Aluno");
            item.setDisplayName(aluno.getNome());
            item.setCreationOrder(getCreationTimestampForAluno(aluno.getUuid()));
            timeline.add(item);
        }

        timeline.sort(Comparator.comparingLong(TimelineItemDTO::getCreationOrder).reversed());

        return timeline;
    }

    private long getCreationTimestampForCurso(UUID cursoUuid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT data_criacao FROM cursos WHERE uuid = ?";
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, cursoUuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("data_criacao");
                return timestamp != null ? timestamp.getTime() : 0L; // Retorna o timestamp em milissegundos
            }
        }
        return 0L;
    }

    private long getCreationTimestampForDisciplina(UUID disciplinaUuid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT data_criacao FROM disciplinas WHERE uuid = ?";
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1,disciplinaUuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("data_criacao");
                return timestamp != null ? timestamp.getTime() : 0L;
            }
        }
        return 0L;
    }

    private long getCreationTimestampForProfessor(UUID professorUuid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT data_criacao FROM professores WHERE uuid = ?";
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, professorUuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("data_criacao");
                return timestamp != null ? timestamp.getTime() : 0L; // Retorna o timestamp em milissegundos
            }
        }
        return 0L;
    }

    private long getCreationTimestampForAluno(UUID alunoUuid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT data_criacao FROM alunos WHERE uuid = ?";
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, alunoUuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("data_criacao");
                return timestamp != null ? timestamp.getTime() : 0L; // Retorna o timestamp em milissegundos
            }
        }
        return 0L;
    }
}
