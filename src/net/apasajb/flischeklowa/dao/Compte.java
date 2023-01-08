package net.apasajb.flischeklowa.dao;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * EntitE qui represente un compte en BDD
 * @author ApasaJB
 */

@Entity
@Table(name="comptes")
@Named
public class Compte implements Serializable {
	
	private static final long serialVersionUID = 3570232884952967059L;
	
	@Id
	@Column(name="id_compte")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompte;
	
	@Column(name="courriel", length=80, nullable=false, unique=true)
	private String courrielCompte;
	
	@Column(name="mot_de_passe", length=20, nullable=false)
	private String mot2Passe;
	
	
	// Constructeurs par defaut et parametrique
	public Compte() {
		super();
	}


	public Compte(int idCompte, String courrielCompte, String mot2Passe) {
		super();
		this.idCompte = idCompte;
		this.courrielCompte = courrielCompte;
		this.mot2Passe = mot2Passe;
	}
	
	
	//==================================================== GETTERS & SETTERS //
	public int getIdCompte() {
		return idCompte;
	}
	
	
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	
	
	public String getCourrielCompte() {
		return courrielCompte;
	}
	
	
	public void setCourrielCompte(String courrielCompte) {
		this.courrielCompte = courrielCompte;
	}
	
	
	public String getMot2Passe() {
		return mot2Passe;
	}
	
	
	public void setMot2Passe(String mot2Passe) {
		this.mot2Passe = mot2Passe;
	}
	//====================================================================== //
}
