package net.apasajb.flischeklowa.ecouteurs;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.apasajb.flischeklowa.operateurs.JSFOperationsSurModelesVols;


/**
 * S'occupe de charger automatique un jour de vols en BDD: au demarrage de l'application.<br/>
 * Si les vols sont deja presents, l'action est ignoree.<br/>
 * @author ApasaJB
 */

public class JobChargementJourVols implements Serializable {
	
	private static final long serialVersionUID = -642466334270650530L;
	
	String messageErreur;
	JSFOperationsSurModelesVols operationsSurModelesVols = new JSFOperationsSurModelesVols();
	
	
	public void autoChargerVols() {
		
		long nombreVolsAujourd8 = 0;
		EntityManager em = EcouteurAppli.getEM();
		String dateAujourd8 = LocalDate.now().toString();
		
		try {
			String reqNombreVols = "SELECT COUNT(v.dateHeure) FROM Vol v WHERE TO_CHAR(v.dateHeure, 'YYYY-MM-DD') = :dateactuelle";
			
			Query req = em.createQuery(reqNombreVols);
			req.setParameter("dateactuelle", dateAujourd8);
			nombreVolsAujourd8 = (long) req.getSingleResult();		// Ici int ne fonctionne pas
			
			if (nombreVolsAujourd8 == 0) {
				operationsSurModelesVols.ajouterJourVols();		// On charge 1 jour de vols, aujourdhui
			}
			
		} catch (Exception ex) {
			messageErreur = "--- Error: auto-loading flights failed. Message: " + ex.getMessage();
			// System.out.println(messageErreur); //> journal_flischeklowa_20220101.log
		}
		
		// A FAIRE: evaluer l'heure pour effectuer le chargement chaque jour A minuit.
	}
}
