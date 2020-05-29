package fr.sma.bw.components;

import fr.sma.bw.entities.JournalTechnique;
import fr.sma.bw.repositories.jdbc.JournalTechniqueJDBC;
import fr.sma.bw.repositories.mybatis.JournalTechniqueDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Profile("prod")
public class DataInitializerDefault implements ApplicationRunner {

    private transient final JournalTechniqueJDBC journalTechniqueJDBC;
    private transient final JournalTechniqueDao journalTechniqueDao;

    public DataInitializerDefault(JournalTechniqueJDBC journalTechniqueJDBC, JournalTechniqueDao journalTechniqueDao) {
        this.journalTechniqueJDBC = journalTechniqueJDBC;
        this.journalTechniqueDao = journalTechniqueDao;
    }

    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<JournalTechnique> jdbcs = this.journalTechniqueJDBC.getAll();
        log.info("Base JOURNAL_TECHNIQUE -->  nombre de lignes JDBC : "+ jdbcs.size());
        List<JournalTechnique> daos = this.journalTechniqueDao.getAll();
        log.info("Base JOURNAL_TECHNIQUE -->  nombre de lignes MyBatis : "+ daos.size());

    }
}
