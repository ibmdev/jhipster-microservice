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

    private transient final UserDao userDao;
    private transient final JournalTechniqueDao journalTechniqueDao;

    public DataInitializer(UserDao userDao, JournalTechniqueDao journalTechniqueDao) {
        this.userDao = userDao;
        this.journalTechniqueDao = journalTechniqueDao;
    }

    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<User> users = this.userDao.getListUsers();
        List<JournalTechnique> jts = this.journalTechniqueDao.getAll();
        log.info("Base User -->  nombre de lignes : "+ users.size());
        log.info("Base JOURNAL_TECHNIQUE -->  nombre de lignes : "+ jts.size());
    }
}