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
            item.setCurtidas(getCurtidasForCurso(curso.getUuid()));
            timeline.add(item);
        }

        for (Disciplina disciplina : disciplinaRepository.listAllDisciplinas()) {
            TimelineItemDTO item = new TimelineItemDTO();
            item.setEntityId(disciplina.getUuid());
            item.setEntityType("Disciplina");
            item.setDisplayName(disciplina.getNome());
            item.setCreationOrder(getCreationTimestampForDisciplina(disciplina.getUuid()));
            item.setCurtidas(getCurtidasForDisciplina(disciplina.getUuid()));
            timeline.add(item);
        }

        for (Professor professor : professorRepository.getAll()) {
            TimelineItemDTO item = new TimelineItemDTO();
            item.setEntityId(professor.getUuid());
            item.setEntityType("Professor");
            item.setDisplayName(professor.getNome());
            item.setCreationOrder(getCreationTimestampForProfessor(professor.getUuid()));
            item.setCurtidas(getCurtidasForProfessor(professor.getUuid()));
            timeline.add(item);
        }

        for (Aluno aluno : alunoRepository.listAllAlunos()) {
            TimelineItemDTO item = new TimelineItemDTO();
            item.setEntityId(aluno.getUuid());
            item.setEntityType("Aluno");
            item.setDisplayName(aluno.getNome());
            item.setCreationOrder(getCreationTimestampForAluno(aluno.getUuid()));
            item.setCurtidas(getCurtidasForAluno(aluno.getUuid()));
            timeline.add(item);
        }

        timeline.sort(Comparator.comparingLong(TimelineItemDTO::getCreationOrder)
                .thenComparing(TimelineItemDTO::getDisplayName)
                .reversed());

        return timeline;
    }

    private long getCreationTimestampForCurso(UUID cursoUuid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT data_criacao FROM cursos WHERE uuid = ?";
        return getTimestamp(sql, cursoUuid);
    }

    private long getCreationTimestampForDisciplina(UUID disciplinaUuid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT data_criacao FROM disciplinas WHERE uuid = ?";
        return getTimestamp(sql, disciplinaUuid);
    }

    private long getCreationTimestampForProfessor(UUID professorUuid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT data_criacao FROM professores WHERE uuid = ?";
        return getTimestamp(sql, professorUuid);
    }

    private long getCreationTimestampForAluno(UUID alunoUuid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT data_criacao FROM alunos WHERE uuid = ?";
        return getTimestamp(sql, alunoUuid);
    }

    private long getTimestamp(String sql, UUID entityUuid) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, entityUuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("data_criacao");
                return timestamp != null ? timestamp.getTime() : 0L;
            }
        }
        return 0L;
    }

    //CURTIDAS LÓGICA

    private int getCurtidasForCurso(UUID cursoUuid) throws SQLException, ClassNotFoundException {
        return getCurtidas("SELECT numero_curtidas FROM cursos WHERE uuid = ?", cursoUuid);
    }

    private int getCurtidasForDisciplina(UUID disciplinaUuid) throws SQLException, ClassNotFoundException {
        return getCurtidas("SELECT numero_curtidas FROM disciplinas WHERE uuid = ?", disciplinaUuid);
    }

    private int getCurtidasForProfessor(UUID professorUuid) throws SQLException, ClassNotFoundException {
        return getCurtidas("SELECT numero_curtidas FROM professores WHERE uuid = ?", professorUuid);
    }

    private int getCurtidasForAluno(UUID alunoUuid) throws SQLException, ClassNotFoundException {
        return getCurtidas("SELECT numero_curtidas FROM alunos WHERE uuid = ?", alunoUuid);
    }


    private int getCurtidas(String sql, UUID entityUuid) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, entityUuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("numero_curtidas");
            }
        }
        return 0;
    }

   // aumentar curtidas
    public void incrementCurtidasForCurso(UUID cursoUuid) throws SQLException, ClassNotFoundException {
        incrementCurtidas("UPDATE cursos SET numero_curtidas = numero_curtidas + 1 WHERE uuid = ?", cursoUuid);
    }

    public void incrementCurtidasForDisciplina(UUID disciplinaUuid) throws SQLException, ClassNotFoundException {
        incrementCurtidas("UPDATE disciplinas SET numero_curtidas = numero_curtidas + 1 WHERE uuid = ?", disciplinaUuid);
    }

    public void incrementCurtidasForProfessor(UUID professorUuid) throws SQLException, ClassNotFoundException {
        incrementCurtidas("UPDATE professores SET numero_curtidas = numero_curtidas + 1 WHERE uuid = ?", professorUuid);
    }

    public void incrementCurtidasForAluno(UUID alunoUuid) throws SQLException, ClassNotFoundException {
        incrementCurtidas("UPDATE alunos SET numero_curtidas = numero_curtidas + 1 WHERE uuid = ?", alunoUuid);
    }

    private void incrementCurtidas(String sql, UUID entityUuid) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, entityUuid);
            stmt.executeUpdate();
        }
    }

}
