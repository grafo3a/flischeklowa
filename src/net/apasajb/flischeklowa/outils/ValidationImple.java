package net.apasajb.flischeklowa.outils;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import org.tinylog.Logger;
import net.apasajb.flischeklowa.dao.Compte;
import net.apasajb.flischeklowa.operateurs.OperationsORMComptes;


/**
 * Offre des methodes qui verifient si les identifiants fournis sont valides.
 * @author ApasaJB
 */

public class ValidationImple implements Validation {
	
	final String regexCourriel = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*@[a-zA-Z0-9]+[.][a-zA-Z]{2,10}$";
	private String erreurLogin;
	
	
	// Validation de l'adresse courriel
	@Override
	public boolean isCourrielValid(String courriel) {
		
		boolean isEmailValid = false;
		
		if (courriel.matches(regexCourriel)) {
			isEmailValid = true;	
		}
		
		return isEmailValid;
	}
	
	
	// Validation du mot de passe
	@Override
	public boolean isMot2passeValid(String mot2passe) {
		
		boolean isPasswordValid = false;
		int nombreMinimumCaracteres = 5;
		
		if (mot2passe.length() >= nombreMinimumCaracteres) {
			isPasswordValid = true;	
		}
		
		return isPasswordValid;
	}
	
	
	// Verif si mot de passe correct en BDD
	@Override
	public boolean isPasswordCorrectInDB(String adresseCourriel, String mot2passe, EntityManager em, Locale locale) {
		
		// On cree un paquet de ressources (un objet ResourceBundle) et on extrait des messages
		String nomBase = "net.apasajb.flischeklowa.ressourcesG11n.paquetServlets";
		ResourceBundle paquetServlets = ResourceBundle.getBundle(nomBase, locale);
		
		// On extrait des messages du paquet de ressources
		String S07_ERREUR_IDENTIFIANTS_KO = paquetServlets.getString("S07_ERREUR_IDENTIFIANTS_KO");
		String S08_ERREUR_EM_ABSENT = paquetServlets.getString("S08_ERREUR_EM_ABSENT");
		
		boolean isPasswordCorrect = false;
		String mot2passeEnBDD;
		OperationsORMComptes operationsORMComptes = new OperationsORMComptes();
		
		try {
			Compte compte = operationsORMComptes.trouverCompteViaCourriel(adresseCourriel, em);
			mot2passeEnBDD = compte.getMot2Passe();
			
			if (mot2passe.equals(mot2passeEnBDD)) {
				isPasswordCorrect = true;
				
			} else {
				erreurLogin = S07_ERREUR_IDENTIFIANTS_KO;
			}
			
		} catch (Exception ex) {
			
			if (em == null) {
				erreurLogin = S08_ERREUR_EM_ABSENT;
				
			} else {
				erreurLogin = S07_ERREUR_IDENTIFIANTS_KO;
			}
			
			Logger.error(erreurLogin + " (" + ex.getMessage() + ")");
			
		} finally {
			try { em.close(); } catch (Exception ignore) {}
		}
		
		return isPasswordCorrect;
	}
	
	
	//====================================================== GETTERS/SETTERS //
	@Override
	public String getErreurLogin() {
		return erreurLogin;
	}
	
	//========================================================================//
}
