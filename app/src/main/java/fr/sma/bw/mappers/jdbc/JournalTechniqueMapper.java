package fr.sma.bw.mappers.jdbc;

import fr.sma.bw.builders.JournalTechniqueBuilder;
import fr.sma.bw.entities.JournalTechnique;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalTechniqueMapper implements RowMapper<JournalTechnique> {
	
	@Override
	public JournalTechnique mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        return new JournalTechniqueBuilder(
        		resultSet.getString("LB_OPERATION"),
        		resultSet.getString("TYPE_OPERATION"),
        		resultSet.getString("DT_OPERATION"),
        		resultSet.getString("CD_RETOUR"),
        		resultSet.getInt("STATUT"))
        .build();
	}
}