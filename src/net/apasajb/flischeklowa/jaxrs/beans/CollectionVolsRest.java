package net.apasajb.flischeklowa.jaxrs.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import net.apasajb.flischeklowa.dao.Vol;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;
import net.apasajb.flischeklowa.outils.SensVol;


/**
 * S'occupe de la compilation d'une collection de vols pour les requetes REST.
 * @author ApasaJB
 */

@Named
@ApplicationScoped
public class CollectionVolsRest implements Serializable {
	
	private static final long serialVersionUID = 3034233759562527526L;
	
	
	public ArrayList<VolRest> compilerCollectionVols(
			LocalDate dateVolRest, String sensVolRest, String codePaysRest, String numeroVolRest){
		
		ArrayList<VolRest> collectionVolsDateSens = new ArrayList<VolRest>();
		EntityManager em = EcouteurAppli.getEM();
		String requeteJPQL;
		TypedQuery<Vol> typedQueryVols;
		
		final String departureUrl = "departure";
		final String arrivalUrl = "arrival";
		String critereSens = " ";		// 1 espace vide ici!
		SensVol sensVol = null;
		
		if (sensVolRest.equals(departureUrl)) {
			sensVol = SensVol.Departure;
			
		} else if (sensVolRest.equals(arrivalUrl)) {
			sensVol = SensVol.Arrival;
		}
		
		if (sensVol == SensVol.Departure || sensVol == SensVol.Arrival) {
			critereSens = " AND v.sens = :sv";
		}
		
		// Cette requete JPQL prend en charge les différentes possibilités de recherche des entités.
		requeteJPQL = "SELECT v FROM Vol v WHERE TO_CHAR(v.dateHeure, 'YYYY-MM-DD') = :dv"
				+ critereSens
				+ " AND v.codePays LIKE :cp"
				+ " AND v.numeroVol LIKE :nv";
		
		typedQueryVols = em.createQuery(requeteJPQL, Vol.class);
		
		if (sensVol == SensVol.Departure || sensVol == SensVol.Arrival) {
			typedQueryVols.setParameter("sv", sensVol);
		}
		
		typedQueryVols.setParameter("dv", dateVolRest.toString());
		typedQueryVols.setParameter("cp", codePaysRest);
		typedQueryVols.setParameter("nv", numeroVolRest);
		
		List<Vol> listeVolsDate = typedQueryVols.getResultList();
		
		if (!listeVolsDate.isEmpty()) {
			
			for (Vol vol : listeVolsDate) {
				
				String dateHeure = vol.getDateHeure().toString().replace("T", " ");
				String sens = vol.getSens().toString();
				String ville = vol.getVille();
				String codePays = vol.getCodePays();
				String compagnie = vol.getCompagnie();
				String numeroVol = vol.getNumeroVol();
				String typeAvion = vol.getTypeAvion();
				String terminal = vol.getTerminal();
				String statut = vol.getStatut();
				
				VolRest VolRest = new VolRest();
				
				VolRest.setDateHeure(dateHeure);
				VolRest.setSens(sens);
				VolRest.setVille(ville);
				VolRest.setCodePays(codePays);
				VolRest.setCompagnie(compagnie);
				VolRest.setNumeroVol(numeroVol);
				VolRest.setTypeAvion(typeAvion);
				VolRest.setTerminal(terminal);
				VolRest.setStatut(statut);
				
				collectionVolsDateSens.add(VolRest);
			}
		}
		
		return collectionVolsDateSens;
	}
}
