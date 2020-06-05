package fr.sma.bw.services;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.sma.bw.entities.JournalTechnique;
import fr.sma.bw.repositories.mybatis.JournalTechniqueDao;

@Component
public class JournalTechniqueService {
	
	  final private JournalTechniqueDao journalTechniqueDao;
	  public JournalTechniqueService(final JournalTechniqueDao journalTechniqueDao) {
		this.journalTechniqueDao = journalTechniqueDao;
	  }
	  public List<JournalTechnique> retrieveJournaux() {
	       return  this.journalTechniqueDao.getAll();
	  }
}
