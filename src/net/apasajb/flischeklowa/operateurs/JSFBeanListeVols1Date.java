package net.apasajb.flischeklowa.operateurs;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import net.apasajb.flischeklowa.dao.Vol;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;


/**
 * Prend en charge les requetes JSF des utilisateurs et<br/>
 * affiche un resultat de recherche sous forme de liste de vols dans un tableau.
 * @author ApasaJB
 */

@Named
@RequestScoped
public class JSFBeanListeVols1Date implements Serializable {
	
	private static final long serialVersionUID = 1143996045564094138L;
	
	private String dateChoisie;
	private ArrayList<String[]> colleTabVolsDate;
	private String messageErreur;
	ServletContext servletContext = EcouteurAppli.getServletContext();
	
	
	public String afficherListeVolsDateChoisie() {
		
		LocalDate dateVol = LocalDate.parse(dateChoisie);
		colleTabVolsDate = this.compilerListeVolsDate(dateVol);
		servletContext.setAttribute("dateChoisie", dateChoisie);
		
		return "liste-vols-date.xhtml";
	}
	
	
	public String afficherListeVolsDateSuivante() {
		
		dateChoisie = (String) servletContext.getAttribute("dateChoisie");
		LocalDate dateVol = null;
		
		try {
			
			dateVol = LocalDate.parse(dateChoisie);
			dateVol = dateVol.plusDays(1);
			this.dateChoisie = dateVol.toString();
			
		} catch (Exception ex) {}
		
		servletContext.setAttribute("dateChoisie", dateChoisie);
		colleTabVolsDate = this.compilerListeVolsDate(dateVol);
		
		return "liste-vols-date.xhtml";
	}
	
	
	public String afficherListeVolsDateFinale() {
		
		LocalDate dateVol = null;
		
		try {
			
			dateVol = this.getDateMaxVols();
			this.dateChoisie = dateVol.toString();
			
		} catch (Exception ignore) {}
		
		servletContext.setAttribute("dateChoisie", dateChoisie);
		colleTabVolsDate = this.compilerListeVolsDate(dateVol);
		
		return "liste-vols-date.xhtml";
	}
	
	
	public String afficherListeVolsDatePrecedente() {
		
		dateChoisie = (String) servletContext.getAttribute("dateChoisie");
		LocalDate dateVol = null;
		
		try {
			
			dateVol = LocalDate.parse(dateChoisie);
			dateVol = dateVol.minusDays(1);
			this.dateChoisie = dateVol.toString();
			
		} catch (Exception ignore) {}
		
		servletContext.setAttribute("dateChoisie", dateChoisie);
		colleTabVolsDate = this.compilerListeVolsDate(dateVol);
		
		return "liste-vols-date.xhtml";
	}
	
	
	public String recommencer() {
		
		this.dateChoisie = null;
		servletContext.setAttribute("dateChoisie", null);
		
		return "liste-vols-date.xhtml";
	}
	
	
	public LocalDate getDateMaxVols() {
		
		EntityManager em = EcouteurAppli.getEM();
		LocalDateTime dateHeureMax;
		LocalDate dateMax = null;
		
		try {
			
			String reqDateMax = "SELECT MAX(v.dateHeure) FROM Vol v";
			Query req = em.createQuery(reqDateMax);
			dateHeureMax = (LocalDateTime) req.getSingleResult();
			dateMax = dateHeureMax.toLocalDate();
			
		} catch (Exception ex) {
			
			if (em == null) {
				messageErreur = "--- Error: No EntityManager found. " + ex.getMessage();
			
			} else {
				messageErreur = "--- Error: any. Message: " + ex.getMessage();
			}
		}
		
		return dateMax;
	}
	
	
	public ArrayList<String[]> compilerListeVolsDate(LocalDate dateVol) {
		
		EntityManager em = EcouteurAppli.getEM();
		ArrayList<String[]> collectionTableauxVols1date = new ArrayList<String[]>();
		
		try {
			String reqListeVolsPour1Date = "SELECT v FROM Vol v WHERE TO_CHAR(v.dateHeure, 'YYYY-MM-DD') = :datechoisie";
			
			TypedQuery<Vol> reqVol = em.createQuery(reqListeVolsPour1Date, Vol.class);
			reqVol.setParameter("datechoisie", dateVol.toString());
			List<Vol> listeVols1Date = reqVol.getResultList();
			
			if (!listeVols1Date.isEmpty()) {
				
				for (Vol vol : listeVols1Date) {
					
					String dateHeure = vol.getDateHeure().toString().replace("T", " ");
					String sens = vol.getSens().toString();
					String ville = vol.getVille();
					String codePays = vol.getCodePays();
					String compagnie = vol.getCompagnie();
					String numeroVol = vol.getNumeroVol();
					String typeAvion = vol.getTypeAvion();
					String terminal = vol.getTerminal();
					String statut = vol.getStatut();
					
					String[] tabVols = {dateHeure, sens, ville, codePays, compagnie, numeroVol, typeAvion, terminal, statut};
					collectionTableauxVols1date.add(tabVols);
				}
			}
			
			if (collectionTableauxVols1date.isEmpty()) {
				messageErreur = "--- Error: Empty list. No flight found for the date: " + dateVol.toString();
			}
			
		} catch (Exception ex) {
			
			if (em == null) {
				messageErreur = "--- Error: No EntityManager found. Message: " + ex.getMessage();
				
			} else {
				messageErreur = "--- Error: any. Message: " + ex.getMessage();
			}
		}
		
		try { em.close(); } catch (Exception ignore) {}
		
		return collectionTableauxVols1date;
	}
	
	
	//====================================================== GETTERS/SETTERS //
	
	public String getDateChoisie() {
		return dateChoisie;
	}
	
	
	public void setDateChoisie(String dateChoisie) {
		this.dateChoisie = dateChoisie;
	}
	
	
	public ArrayList<String[]> getColleTabVolsDate() {
		return colleTabVolsDate;
	}
	
	
	public void setColleTabVolsDate(ArrayList<String[]> colleTabVolsDate) {
		this.colleTabVolsDate = colleTabVolsDate;
	}
	
	
	public String getMessageErreur() {
		return messageErreur;
	}
	
	
	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}
	
	//=========================================================================//
}
