package fr.sma.bw.mappers.jdbc;

import fr.sma.bw.entities.JournalTechnique;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalTechniqueMapper implements RowMapper<JournalTechnique> {
    public JournalTechnique mapRow(ResultSet rs, int rowNum) throws SQLException {
        JournalTechnique journal = new JournalTechnique();
        journal.setIdOperation(rs.getInt("ID_OPERATION"));
        return journal;
    }
}