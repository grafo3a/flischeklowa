package net.apasajb.flischeklowa.outils;

import javax.servlet.ServletContext;
import org.apache.tomcat.jdbc.pool.DataSource;


/**
 * Cette interface definit l'exploitation (demarrage et arret) d'un Pool de connexions.
 * @author ApasaJB
 */

public interface BoitePoolConnexion {
	
	
	/**
	 * Demarre un pool de connexions;
	 */
	public void demarrerPool(ServletContext sletContext);
	
	
	/**
	 * Arrete un pool de connexions;
	 */
	public void arreterPool(ServletContext sletContext);
	
	
	//====================================================== GETTERS/SETTERS //
	
	/**
	 * Accesseur (Getter) pour un pool de connexions.
	 */
	public DataSource getPoolConnex(ServletContext sletContext);
	
	
	/**
	 * Mutateur (Setter) pour un pool de connexions.
	 */
	public void setPoolConnex(DataSource poolConnex);
	
	
	/**
	 * Accesseur (Getter) pour un resultat de demarrage ou d'arret d'un pool de connexions.
	 */
	public String getResultatPool();
	
	
	/**
	 * Mutateur (Setter) pour un resultat de demarrage ou d'arret d'un pool de connexions.
	 */
	public void setResultatPool(String resultatPool);
	
	//==========================================================//
}
