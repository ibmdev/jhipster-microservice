package fr.sma.bw.entities;


import java.io.Serializable;
import java.util.Date;
import fr.sma.bw.builders.JournalTechniqueBuilder;

public class JournalTechnique implements Serializable {

    private static final long serialVersionUID = -32138168293547435L;

	private Integer idOperation;
    private String lbOperation;
    private String typeOperation;
    private String dateOperation;
    private String idCorrelation;
    private Date dateRetourOperation;
    private int statut;
    private String idDemande;
    private String cdRetour;
    private String lbRetour;
    private byte[] fluxEntree;
    private String cdApplication;
    private byte[] fluxRetour;
    
    public JournalTechnique() {
    	super();
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(final Integer idOperation) {
        this.idOperation = idOperation;
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

    public String getIdCorrelation() {
        return idCorrelation;
    }

    public void setIdCorrelation(final String idCorrelation) {
        this.idCorrelation = idCorrelation;
    }

    public Date getDateRetourOperation() {
        return copyDate(dateRetourOperation);
    }

    public void setDateRetourOperation(final Date dateRetourOperation) {
        this.dateRetourOperation = copyDate(dateRetourOperation);
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(final int statut) {
        this.statut = statut;
    }

    public String getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(final String idDemande) {
        this.idDemande = idDemande;
    }

    public String getCdRetour() {
        return cdRetour;
    }

    public void setCdRetour(final String cdRetour) {
        this.cdRetour = cdRetour;
    }

    public String getLbRetour() {
        return lbRetour;
    }

    public void setLbRetour(final String lbRetour) {
        this.lbRetour = lbRetour;
    }

    public byte[] getFluxEntree() {
        return cloneArrayBytes(fluxEntree);
    }

    public void setFluxEntree(final byte[] fluxEntree) {
        this.fluxEntree = cloneArrayBytes(fluxEntree);
    }

    public String getCdApplication() {
        return cdApplication;
    }

    public void setCdApplication(final String cdApplication) {
        this.cdApplication = cdApplication;
    }

    public byte[] getFluxRetour() {
        return cloneArrayBytes(fluxRetour);
    }

    public void setFluxRetour(final byte[] fluxRetour) {
        this.fluxRetour = cloneArrayBytes(fluxRetour);
    }

    private byte[] cloneArrayBytes(final byte[] bytes) {
    	if(bytes != null) {
    		return bytes.clone();
    	}
    	return new byte[0];
        
    }
    private Date copyDate(final Date date) {
        if(date != null) {
        	return new Date(date.getTime());
        }
        return null;
    }
    public JournalTechnique(final JournalTechniqueBuilder builder) {
        this.lbOperation = builder.getLbOperation();
    	this.typeOperation = builder.getTypeOperation();
    	this.dateOperation = builder.getDateOperation();
    	this.cdRetour = builder.getCdRetour();
    	this.statut = builder.getStatut();
        
    }
}
