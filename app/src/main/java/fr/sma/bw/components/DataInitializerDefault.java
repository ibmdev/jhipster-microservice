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

  private final JournalTechniqueJDBC journalTechniqueJDBC;
  private final JournalTechniqueDao journalTechniqueDao;

  public DataInitializerDefault(final JournalTechniqueJDBC journalTechniqueJDBC, final JournalTechniqueDao journalTechniqueDao) {
    this.journalTechniqueJDBC = journalTechniqueJDBC;
    this.journalTechniqueDao = journalTechniqueDao;
  }

  @Override
  public void run(final ApplicationArguments applicationArguments) throws Exception {
    final List<JournalTechnique> jdbcs = this.journalTechniqueJDBC.getAll();
    final List<JournalTechnique> daos = this.journalTechniqueDao.getAll();
    if(log.isInfoEnabled()) {
    	log.info("Base JOURNAL_TECHNIQUE -->  nombre de lignes JDBC : "+ jdbcs.size());
        log.info("Base JOURNAL_TECHNIQUE -->  nombre de lignes MyBatis : "+ daos.size());
    }
   }
}
