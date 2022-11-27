package net.apasajb.flischeklowa.outils;


/**
 * Offre des methodes qui verifient si les identifiants fournis sont valides.
 * @author ApasaJB
 */

public class Validation {
	
	static final String regexCourriel = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*@[a-zA-Z0-9]+[.][a-zA-Z]{2,10}$";
	static boolean isValid;
	
	
	// Validation de l'adresse courriel
	public static boolean validerCourriel (String courriel88) {
		
		if (courriel88.matches(regexCourriel)) {
			isValid = true;
			
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	
	// Validation du mot de passe
	public static boolean validerMot2passe(String mot2passe) {
		
		if (mot2passe.length() >= 5) {
			isValid = true;
			
		} else {
			isValid = false;
		}
		
		return isValid;
	}
}
