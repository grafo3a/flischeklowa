package net.apasajb.flischeklowa.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import net.apasajb.flischeklowa.outils.SensVol;


/**
 * EntitE qui represente un vol en BDD
 * @author ApasaJB
 */

@Entity
@Table(name="vols55")
@Named
public class Vol implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_vol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVol;
	
	@Column(name="code_pays", length=2, nullable=false)
	private String codePays;
	
	@Column(length=50, nullable=false)
	private String compagnie;
	
	@Column(name="date_heure", nullable=false, unique=true)
	private LocalDateTime dateHeure;
	
	@Column(name="numero_vol", length=50)
	private String numeroVol;
	
	@Column(length=10, nullable=false)
	@Enumerated(EnumType.STRING)
	private SensVol sens;
	
	@Column(length=50)
	private String statut;
	
	@Column(length=5)
	private String terminal;
	
	@Column(name="type_avion", length=50)
	private String typeAvion;
	
	@Column(length=50, nullable=false)
	private String ville;
	
	
	// Constructeurs par defaut et parametrique
	
	public Vol() {
		super();
	}
	
	
	public Vol(int idVol, LocalDateTime dateHeure, SensVol sens, String ville, String codePays, String compagnie,
			String codeCompagnie, String numeroVol, String typeAvion, String terminal, String statut) {
		
		this.setIdVol(idVol);
		this.setCodePays(codePays);
		this.setCompagnie(compagnie);
		this.setDateHeure(dateHeure);
		this.setNumeroVol(numeroVol);
		this.setSens(sens);
		this.setStatut(statut);
		this.setTerminal(terminal);
		this.setTypeAvion(typeAvion);
		this.setVille(ville);
	}
	
	
	//==================================================== GETTERS & SETTERS //
	
	public int getIdVol() {
		return idVol;
	}
	
	
	public void setIdVol(int idVol) {
		this.idVol = idVol;
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
	
	
	public LocalDateTime getDateHeure() {
		return dateHeure;
	}
	
	
	public void setDateHeure(LocalDateTime dateHeure) {
		this.dateHeure = dateHeure;
	}
	
	
	public String getNumeroVol() {
		return numeroVol;
	}
	
	
	public void setNumeroVol(String numeroVol) {
		this.numeroVol = numeroVol;
	}
	
	
	public SensVol getSens() {
		return sens;
	}
	
	
	public void setSens(SensVol sens) {
		this.sens = sens;
	}
	
	
	public String getStatut() {
		return statut;
	}
	
	
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
	public String getTerminal() {
		return terminal;
	}
	
	
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	
	public String getTypeAvion() {
		return typeAvion;
	}
	
	
	public void setTypeAvion(String typeAvion) {
		this.typeAvion = typeAvion;
	}
	
	
	public String getVille() {
		return ville;
	}
	
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	//=======================================================================//
}
