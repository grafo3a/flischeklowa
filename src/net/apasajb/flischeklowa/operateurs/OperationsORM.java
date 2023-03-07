package net.apasajb.flischeklowa.operateurs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import net.apasajb.flischeklowa.dao.Vol;
import net.apasajb.flischeklowa.outils.SensVol;


/**
 * <br/>Fournit des methodes reutilisables permettant de:
 * - Trouver les infos d'un vol sur base d'un moment date-heure;<br/>
 * - Persister (modifier) une information d'un vol en BDD.<br/>
 * @author ApasaJB
 */

@Named
public class OperationsORM {
	
	private Vol vol;
	private String erreurAjoutVol;
	
	
	/**
	 * @param nomParam
	 * <br/>Une des chaines de caracteres suivantes: "Hour", "PlaneType", "Gate", "Status".<br/>
	 * NB: dateHeure doit avoir la forme "yyyy-MM-ddThh:mm".
	 * @param valeurParam
	 * <br/>Une des variables suivantes: heureChoisie, typeAvion, terminal, statut.
	 */
	
	public Vol persisterModification1Vol(String nomParam, String valeurParam, EntityManager em, HttpSession session) {
		
		int idEnSession = (int) session.getAttribute("idEnSession");
		vol = em.find(Vol.class, idEnSession);
		
		if (vol != null) {
			
			LocalDateTime dateHeure;
			
			switch (nomParam) {
			
			case "Hour":
				LocalDate dateEnBdd = vol.getDateHeure().toLocalDate();
				dateHeure = LocalDateTime.parse(dateEnBdd + "T" + valeurParam);
				vol.setDateHeure(dateHeure);
				break;
				
			case "PlaneType":
				vol.setTypeAvion(valeurParam);
				break;
				
			case "Gate":
				vol.setTerminal(valeurParam);
				break;
				
			case "Status":
				vol.setStatut(valeurParam);
				break;
				
			default:
				break;
			}
			
			try {
				
				em.getTransaction().begin();
				em.persist(vol);
				em.getTransaction().commit();
				
			} catch (Exception ex) {
				em.getTransaction().rollback();
			}
		}
		
		return vol;
	}
	
	
	/**
	 *Lit les details d'un vol sur base d'un moment date-heure.
	 * @return Vol
	 */
	public Vol lireVolViaDateHeure (String dateVol, String heureVol, EntityManager em) {
		
		LocalDateTime moment = LocalDateTime.parse(dateVol + "T" + heureVol);
		String chaineReq = "SELECT v FROM Vol v WHERE v.dateHeure = :m";
		LocalDateTime m = moment;
		TypedQuery<Vol> reqVol = em.createQuery(chaineReq, Vol.class);
		reqVol.setParameter("m", m);
		vol = (Vol) reqVol.getSingleResult();
		
		return vol;
	}
	
	
	/**
	 * Persiste un vol en BDD.
	 */
	public void persisterVol(SensVol sens, LocalDateTime dateHeure, String ville, String codePays,
								String compagnie, String numeroVol, String typeAvion, String terminal, EntityManager em) {
				
		Vol vol = new Vol();
		vol.setCodePays(codePays);
		vol.setCompagnie(compagnie);
		vol.setDateHeure(dateHeure);
		vol.setNumeroVol(numeroVol);
		vol.setSens(sens);
		vol.setStatut("Confirmed");
		vol.setVille(ville);
		vol.setTerminal(terminal);
		vol.setTypeAvion(typeAvion);
		
		try {
			
			em.getTransaction().begin();
			em.persist(vol);
			em.getTransaction().commit();
			
		} catch (Exception ex) {
			
			em.getTransaction().rollback();
			erreurAjoutVol = ex.getMessage();
			
		} finally {
			try { em.close(); } catch (Exception ignore) {}
		}
	}
	
	
	//====================================================== GETTERS/SETTERS //
	
	public String getErreurAjoutVol() {
		return erreurAjoutVol;
	}
	
	
	public void setErreurAjoutVol(String erreurAjoutVol) {
		this.erreurAjoutVol = erreurAjoutVol;
	}
	//=========================================================================//
}
