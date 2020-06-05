package fr.sma.bw.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.sma.bw.entities.JournalTechnique;
import fr.sma.bw.services.JournalTechniqueService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JournalTechniqueController {
	
	private final JournalTechniqueService journalTechniqueService;
	
	public JournalTechniqueController(final JournalTechniqueService journalTechniqueService) {
		this.journalTechniqueService = journalTechniqueService;
	}
	@GetMapping("/api/journaux")
	public ResponseEntity<List<JournalTechnique>> getJournaux() {
	  final List<JournalTechnique> journaux = this.journalTechniqueService.retrieveJournaux();
	  HttpStatus codeRetour = HttpStatus.OK;
	  if(journaux.isEmpty()) {
		  log.debug("La table des journaux technique est vide, aucun enregistrement");
		  codeRetour = HttpStatus.NOT_FOUND;
	  }
	  return new ResponseEntity<>(journaux, codeRetour);
	  }
}
