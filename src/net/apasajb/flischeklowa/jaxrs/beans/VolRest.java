package net.apasajb.flischeklowa.jaxrs.beans;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Bean JAX-RS qui contient les infos d'une entitE Vol qui sera produite en XML ou JSON.
 * @author ApasaJB
 */

@XmlRootElement
public class VolRest {
	
	String dateHeure;
	String sens;
	String ville;
	String codePays;
	String compagnie;
	String numeroVol;
	String typeAvion;
	String terminal;
	String statut;
	
	
	//====================================================== GETTERS/SETTERS //
	
	public String getDateHeure() {
		return dateHeure;
	}
	
	
	public void setDateHeure(String dateHeure) {
		this.dateHeure = dateHeure;
	}
	
	
	public String getSens() {
		return sens;
	}
	
	
	public void setSens(String sens) {
		this.sens = sens;
	}
	
	
	public String getVille() {
		return ville;
	}
	
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	public String getCodePays() {
		return codePays;
	}
	
	
	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}
	
	
	public String getCompagnie() {
		return compagnie;
	}
	
	
	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}
	
	
	public String getNumeroVol() {
		return numeroVol;
	}
	
	
	public void setNumeroVol(String numeroVol) {
		this.numeroVol = numeroVol;
	}
	
	
	public String getTypeAvion() {
		return typeAvion;
	}
	
	
	public void setTypeAvion(String typeAvion) {
		this.typeAvion = typeAvion;
	}
	
	
	public String getTerminal() {
		return terminal;
	}
	
	
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	
	public String getStatut() {
		return statut;
	}
	
	
	public void setStatut(String statut) {
		this.statut = statut;
	}
	//=========================================================================//
}
