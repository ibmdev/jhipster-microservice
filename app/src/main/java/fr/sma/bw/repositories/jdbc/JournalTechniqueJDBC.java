package fr.sma.bw.repositories.jdbc;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import fr.sma.bw.constants.EnumConstants;
import fr.sma.bw.entities.JournalTechnique;
import fr.sma.bw.mappers.jdbc.JournalTechniqueMapper;

@Component
public class JournalTechniqueJDBC extends JdbcDaoSupport {

    private final JdbcTemplate jdbcTemplate;
    public JournalTechniqueJDBC(final JdbcTemplate jdbcTemplate) {
    super();	
    this.jdbcTemplate = jdbcTemplate;
    setJdbcTemplate(this.jdbcTemplate);
    }
    public List<JournalTechnique>  getAll() {
    return this.jdbcTemplate.query(EnumConstants.SELECT_JT_FIRST_TEN, new JournalTechniqueMapper());
    }
}
