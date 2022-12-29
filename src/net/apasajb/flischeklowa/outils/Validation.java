package net.apasajb.flischeklowa.outils;


/**
 * Offre des methodes qui verifient si les identifiants fournis sont valides.
 * @author ApasaJB
 */

public interface Validation {
	
	// Validation de l'adresse courriel
	public boolean validerCourriel (String courriel88);
	
	
	// Validation du mot de passe
	public boolean validerMot2passe(String mot2passe);
}
