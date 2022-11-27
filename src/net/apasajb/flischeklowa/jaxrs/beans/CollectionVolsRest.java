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


/**
 * S'occupe de la compilation d'une collection de vols pour les requetes REST.
 * @author ApasaJB
 */

@Named
@ApplicationScoped
public class CollectionVolsRest implements Serializable {
	
	private static final long serialVersionUID = 3034233759562527526L;
	
	
	public ArrayList<VolRest> compilerCollectionVols(LocalDate dateVolRest, String codePaysRest, String numeroVolRest){
		
		ArrayList<VolRest> collectionVolsDateSens = new ArrayList<VolRest>();
		EntityManager em = EcouteurAppli.getEM();
		String chaineReq;
		TypedQuery<Vol> reqVol;
		
		// Cette requete prend en charge les differentes possibilites de recherche des entitEs.
		chaineReq = "SELECT v FROM Vol v WHERE TO_CHAR(v.dateHeure, 'YYYY-MM-DD') = :dv"
				+ " AND v.codePays LIKE :cp"
				+ " AND v.numeroVol LIKE :nv";
		
		reqVol = em.createQuery(chaineReq, Vol.class);
		
		reqVol.setParameter("dv", dateVolRest.toString());
		reqVol.setParameter("cp", codePaysRest);
		reqVol.setParameter("nv", numeroVolRest);
		
		List<Vol> listeVolsDate = reqVol.getResultList();
		
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
