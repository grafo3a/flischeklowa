package net.apasajb.flischeklowa.outils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;


/**
 * Implementation de l'interface BoiteEMFactory
 * @author ApasaJB
 */

public class BoiteEMFactoryImple implements BoiteEMFactory {
	
	private EntityManagerFactory emFactory;
	private String resultatEMFactory;
	
	
	public void demarrerEMFactory(ServletContext sletContext) {
		
		String persistenceUnit = "PU_Vols";		//--- L'unite de persistence est definie dans persistence.xml
		
		try {
			
			emFactory = Persistence.createEntityManagerFactory(persistenceUnit);
			resultatEMFactory = "--- The EntityManagerFactory has started successfully.";
			
		} catch(Exception ex) {
			resultatEMFactory = "--- Error: The EntityManagerFactory failed to start! Message: " + ex.getMessage();
			
		} finally {
			
			sletContext.setAttribute("emFactory", emFactory);
			sletContext.setAttribute(resultatEMFactory, resultatEMFactory);
		}
	}
	
	
	public void arreterEMFactory(ServletContext contexte) {
		
		try {
			
			emFactory = (EntityManagerFactory) contexte.getAttribute("emFactory");
			emFactory.close();
			
		} catch (Exception ignore){
		} finally {
			
			resultatEMFactory = "--- The EntityManagerFactory has been stopped.";
			contexte.setAttribute("emFactory", null);
			this.setResultatEMFactory(resultatEMFactory);
		}
	}
	
	
	//====================================================== GETTERS/SETTERS //
	
	public EntityManagerFactory getEmFactory() {
		
		ServletContext sletContext = EcouteurAppli.getServletContext();
		emFactory = (EntityManagerFactory) sletContext.getAttribute("emFactory");
		
		return emFactory;
	}
	
	
	public void setEmFactory(EntityManagerFactory emFactory) {
		this.emFactory = emFactory;
	}
	
	
	public String getResultatEMFactory() {
		return resultatEMFactory;
	}
	
	
	public void setResultatEMFactory(String resultatEMFactory) {
		this.resultatEMFactory = resultatEMFactory;
	}
	//=========================================================================//
}
