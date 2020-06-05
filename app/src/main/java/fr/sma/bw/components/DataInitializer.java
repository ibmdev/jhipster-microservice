package fr.sma.bw.components;

import fr.sma.bw.entities.JournalTechnique;
import fr.sma.bw.entities.User;
import fr.sma.bw.repositories.mybatis.JournalTechniqueDao;
import fr.sma.bw.repositories.mybatis.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Profile("dev")
@Slf4j
public class DataInitializer implements ApplicationRunner {

  private final UserDao userDao;
  private final JournalTechniqueDao journalTechniqueDao;

  public DataInitializer(final UserDao userDao, final JournalTechniqueDao journalTechniqueDao) {
    this.userDao = userDao;
    this.journalTechniqueDao = journalTechniqueDao;
  }

  @Override
  public void run(final ApplicationArguments applicationArguments) throws Exception {
    final List<User> users = this.userDao.getListUsers();
    final List<JournalTechnique> jts = this.journalTechniqueDao.getAll();
    if(log.isInfoEnabled()) {
    	log.info("Base User -->  nombre de lignes : "+ users.size());
        log.info("Base JOURNAL_TECHNIQUE -->  nombre de lignes : "+ jts.size());
    }
  }
}
