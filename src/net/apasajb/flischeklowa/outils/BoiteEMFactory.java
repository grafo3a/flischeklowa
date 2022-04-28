package net.apasajb.flischeklowa.outils;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;


/**
 * Cette interface definit l'exploitation (demarrage et arret) d'une fabrique EntityManagerFactory.
 * @author ApasaJB
 */

public interface BoiteEMFactory {
	
	
	/**
	 * Demarre une fabrique EntityManagerFactory et sauvegarde la fabrique dans l'objet contexte de l'application.
	 */
	public void demarrerEMFactory(ServletContext sletContext);
	
	
	/**
	 * Arrete une fabrique EntityManagerFactory.
	 */
	public void arreterEMFactory(ServletContext sletContext);
	
	
	//====================================================== GETTERS/SETTERS //
	/**
	 * Accesseur (Getter) pour une fabrique EntityManagerFactory.
	 */
	public EntityManagerFactory getEmFactory();
	
	
	/**
	 * Mutateur (Setter) pour une fabrique EntityManagerFactory.
	 */
	public void setEmFactory(EntityManagerFactory emFactory);
	
	
	/**
	 * Accesseur (Getter) pour un resultat de demarrage ou d'arret d'une fabrique EntityManagerFactory.
	 */
	public String getResultatEMFactory();
	
	
	/**
	 * Mutateur (Setter) pour un resultat de demarrage ou d'arret d'une fabrique EntityManagerFactory.
	 */
	public void setResultatEMFactory(String resultatEMFactory);
	
	//=========================================================================//
}
