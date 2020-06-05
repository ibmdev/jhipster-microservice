package fr.sma.bw.builders;

import fr.sma.bw.entities.JournalTechnique;

public class JournalTechniqueBuilder {
	
	private String lbOperation;
    private String typeOperation;
    private String dateOperation;
    private String cdRetour;
    private int statut;
    
    
	public JournalTechniqueBuilder(
			final String lbOperation, 
			final String typeOperation, 
			final String dateOperation,
			final String cdRetour, 
			final int statut) {
		super();
		this.lbOperation = lbOperation;
		this.typeOperation = typeOperation;
		this.dateOperation = dateOperation;
		this.cdRetour = cdRetour;
		this.statut = statut;
	}

	public String getLbOperation() {
		return lbOperation;
	}

	public void setLbOperation(final String lbOperation) {
		this.lbOperation = lbOperation;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(final String typeOperation) {
		this.typeOperation = typeOperation;
	}
	public String getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(final String dateOperation) {
		this.dateOperation = dateOperation;
	}
	public String getCdRetour() {
		return cdRetour;
	}
	public void setCdRetour(final String cdRetour) {
		this.cdRetour = cdRetour;
	}
	public JournalTechnique build() {
        return new JournalTechnique(this);
    }

	public int getStatut() {
		return statut;
	}

	public void setStatut(final int statut) {
		this.statut = statut;
	}
	
}
