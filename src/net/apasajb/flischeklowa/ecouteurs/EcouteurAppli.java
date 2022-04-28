package net.apasajb.flischeklowa.ecouteurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import net.apasajb.flischeklowa.jaxrs.beans.CollectionVolsRest;
import net.apasajb.flischeklowa.outils.BoiteEMFactory;
import net.apasajb.flischeklowa.outils.BoiteEMFactoryImple;
import net.apasajb.flischeklowa.outils.BoitePoolConnexion;
import net.apasajb.flischeklowa.outils.BoitePoolConnexionImple;


/**
 * Demarre automatiquement: un pool de connexions, une fabrique JPA et les stocke dans le contexte applicatif;<br/>
 * Offre des methodes statiques permettant d'obtenir: un gestionnaire d'entitE (EntityManager), le contexte applicatif &
 * une collection de vols pour les requetes REST;<br/>
 * Charge un jour de vol au demarrage de l'application (si l'horaire du jour est vide).
 * @author ApasaJB
 */

@WebListener
public class EcouteurAppli implements ServletContextListener {
	
	private static final String fuseauHoraire = "Europe/Warsaw";		//--- Le fuseau horaire de Klow
	private static ServletContext servletContext = null;
	private static CollectionVolsRest collectionVolsRest = new CollectionVolsRest();
	
	BoitePoolConnexion objetPool = new BoitePoolConnexionImple();
	BoiteEMFactory objetEmf = new BoiteEMFactoryImple();
	
	
	public static EntityManager getEM() {
		
		EntityManager em = null;
		
		try {
			
			EntityManagerFactory emFactory = (EntityManagerFactory) servletContext.getAttribute("emFactory");
			em = emFactory.createEntityManager();
			
		} catch (Exception ignore) {}
		
		return em;
	}
	
	
	//--- A L'ARRET DE L'APPLICATION
	@Override
	public void contextDestroyed(ServletContextEvent sce)  {
		
		servletContext = sce.getServletContext();
		
		objetPool.arreterPool(servletContext);
		objetEmf.arreterEMFactory(servletContext);
	}
	
	
	//--- AU DEMARRAGE DE L'APPLICATION
	@Override
	public void contextInitialized(ServletContextEvent sce)  {
		
		servletContext = sce.getServletContext();
		EcouteurAppli.setServletContext(servletContext);
		
		objetPool.demarrerPool(servletContext);
		objetEmf.demarrerEMFactory(servletContext);
		
		//--- Chargement automatique d'un jour de vols si l'horaire du jour est vide
		JobChargementJourVols job = new JobChargementJourVols();
		job.autoChargerVols();
	}
	
	
	//====================================================== GETTERS/SETTERS //
	
	public static String getFuseauhoraire() {
		return fuseauHoraire;
	}
	
	
	public static ServletContext getServletContext() {
		return servletContext;
	}
	
	
	public static void setServletContext(ServletContext sletContext) {
		EcouteurAppli.servletContext = sletContext;
	}
	
	
	public static CollectionVolsRest getCollectionVolsRest() {
		return collectionVolsRest;
	}
	
	
	public static void setCollectionVolsRest(CollectionVolsRest collectionVolsRest) {
		EcouteurAppli.collectionVolsRest = collectionVolsRest;
	}
	//==========================================================================//
}
