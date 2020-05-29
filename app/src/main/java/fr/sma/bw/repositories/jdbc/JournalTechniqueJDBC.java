package fr.sma.bw.repositories.jdbc;

import fr.sma.bw.entities.JournalTechnique;
import fr.sma.bw.mappers.jdbc.JournalTechniqueMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class JournalTechniqueJDBC extends JdbcDaoSupport {

    transient final static String getAllSQL = "SELECT * from JOURNAL_TECHNIQUE FETCH FIRST 10 ROWS ONLY";
    private transient final JdbcTemplate jdbcTemplate;
    public JournalTechniqueJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostConstruct
    private void initialize() {
        setJdbcTemplate(this.jdbcTemplate);
    }
    public List <JournalTechnique>  getAll() {
        if(this.jdbcTemplate !=null) {
            return this.jdbcTemplate.query(getAllSQL, new JournalTechniqueMapper());
        }
        return null;
    }
}
