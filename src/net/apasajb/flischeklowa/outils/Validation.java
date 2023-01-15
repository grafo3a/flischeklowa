package net.apasajb.flischeklowa.outils;

import javax.persistence.EntityManager;


/**
 * Offre des methodes qui verifient si les identifiants fournis sont valides.
 * @author ApasaJB
 */

public interface Validation {
	
	// Validation de l'adresse courriel
	public boolean isCourrielValid (String courriel);
	
	
	// Validation du mot de passe
	public boolean isMot2passeValid(String mot2passe);
	
	
	// Verif si mot de passe correct en BDD
	public boolean isPasswordCorrectInDB(String adresseCourriel, String mot2passe, EntityManager em);
	
	
	// Accesseur pour ErreurLogin (c'est un getter)
	public String getErreurLogin();
}
