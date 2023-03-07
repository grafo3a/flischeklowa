package net.apasajb.flischeklowa.operateurs;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import net.apasajb.flischeklowa.dao.Vol;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;
import net.apasajb.flischeklowa.outils.SensVol;


/**
 * Classe ManagedBean JSF liee A la page d'affichage details-vol.xhtml<br/>
 * et accessible via l'URL details-vol.jsf.
 *  @author ApasaJB
 */

@Named
@RequestScoped
public class JSFBeanDetails1Vol implements Serializable {
	
	private static final long serialVersionUID = 8360627173725582497L;
	
	private int idVol;
	private LocalDateTime dateHeure;
	private SensVol sens;
	private String ville;
	private String codePays;
	private String compagnie;
	private String numeroVol;
	private String typeAvion;
	private String terminal;
	private String statut;
	private String dateChoisie;
	private String heureChoisie;
	private String momentVol;
	private String erreurLecture;
	private String messageErreur;
	private String messageSucces;
	private final String regexDate ="^([2][0-9][0-9][0-9])-([0][1-9]|[1][012])-([0][1-9]|[12][0-9]|[3][01])$";
	private final String regexHeure = "^([01][0-9]|[2][0-3]):([0-5][0-9])$";
	private EntityManager em;
	private HttpSession session;
	private OperationsORM operationsORM = new OperationsORM();
	private Vol vol;
	
	
	public String lireDetailsVol() {
		
		try {
			
			em = EcouteurAppli.getEM();
			vol = operationsORM.lireVolViaDateHeure(dateChoisie, heureChoisie, em);
			
		} catch (Exception ex) {
			
			erreurLecture = "--- Error. Message: " + ex.getMessage();
			
			if (em == null) {
				erreurLecture = "--- Error: EntityManager not found.";
				
			} else if (vol == null) {
				erreurLecture = "--- Info: Flight not found for " + dateChoisie + " " + heureChoisie + "." ;
			}
			
		} finally {
			
			// Preparation pour affichage de la page
			this.mettreAjourAffichageVol(vol);
			HttpSession session = this.getSession();
			session.setAttribute("idEnSession", idVol);
		}
		
		try { em.close(); } catch (Exception ignore) {}
		
		return "details-vol.xhtml";
	}
	
	
	//======================================= UTILISATION DE modifierInfoVol(*) //
	
	public void modifierInfoDate() {
		/* La date d'un vol ne peut pas changer.
		 * En cas d'annulation, les billets sont assignEs au vol d'une autre date. */
	}
	
	
	public void modifierInfoHeure() {
		this.modifierInfoVol("Hour", heureChoisie, regexHeure);
	}
	
	
	public void modifierInfoTypeAvion() {
		this.modifierInfoVol("PlaneType", typeAvion, "^[a-zA-Z_0-9].{1,30}$");		// Tout mot valide de 1 A 30 caracteres
	}
	
	
	public void modifierInfoTerminal() {
		this.modifierInfoVol("Gate", terminal, "^[a-zA-Z_0-9].{1,30}$");		// Tout mot de 1 A 10 caracteres	
	}
	
	
	public void modifierInfoStatut() {
		this.modifierInfoVol("Status", statut, "^[a-zA-Z_0-9].{1,30}$");		// Tout mot de 3 A 10 caracteres
	}
	
	
	public String modifierInfoVol(String nomInfo, String infoVol, String regexInfo) {
		
		vol = null;
		session = this.getSession();
		int idEnSession = (int) session.getAttribute("idEnSession");
		em = EcouteurAppli.getEM();
		
		if (infoVol.matches(regexInfo)) {
			
			try {
				
				vol = operationsORM.persisterModification1Vol(nomInfo, infoVol, em, session);
				messageSucces = "--- " + nomInfo + " updated successfully.";
				
			} catch (Exception ex) {
				setmessageErreur("--- Error. The update failed. Message: " + ex.getMessage());
			}
			
		} else {
			// Si test regex KO
			
			setmessageErreur("--- Error: " + nomInfo + " not valid (" + infoVol + ")");
			
			try {
				vol = em.find(Vol.class, idEnSession);
			
			} catch (Exception ignore) {}
		}
		
		try { this.mettreAjourAffichageVol(vol); } catch (Exception ignore) {}
		
		try { em.close(); } catch (Exception ignore) {}
		
		return "details-vol.xhtml";
	}
	
	
	public void mettreAjourAffichageVol(Vol vol) {
		
		if (vol != null) {
			
			idVol = vol.getIdVol();
			dateHeure = vol.getDateHeure();
			sens = vol.getSens();
			ville = vol.getVille();
			codePays = vol.getCodePays();
			compagnie = vol.getCompagnie();
			numeroVol = vol.getNumeroVol();
			typeAvion = vol.getTypeAvion();
			terminal = vol.getTerminal();
			statut = vol.getStatut();
			dateChoisie = dateHeure.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			heureChoisie = dateHeure.format(DateTimeFormatter.ofPattern("HH:mm"));
			momentVol = dateHeure.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " " + heureChoisie;
		}
		
		if (idVol > 0) {
			
			// Enregistrement de idVol en session
			HttpSession session = this.getSession();
			session.setAttribute("idEnSession", idVol);
		}
	}
	
	
	//====================================================== GETTERS/SETTERS //
	
	public int getIdVol() {
		return idVol;
	}
	
	
	public void setIdVol(int idVol) {
		this.idVol = idVol;
	}
	
	
	public LocalDateTime getDateHeure() {
		return dateHeure;
	}
	
	
	public void setDateHeure(LocalDateTime dateHeure) {
		this.dateHeure = dateHeure;
	}
	
	
	public SensVol getSens() {
		return sens;
	}
	
	
	public void setSens(SensVol sens) {
		this.sens = sens;
	}
	
	
	public String getVille() {
		return ville;
	}
	
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	public String getCodePays() {
		return codePays;
	}
	
	
	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}
	
	
	public String getCompagnie() {
		return compagnie;
	}
	
	
	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}
	
	
	public String getNumeroVol() {
		return numeroVol;
	}
	
	
	public void setNumeroVol(String numeroVol) {
		this.numeroVol = numeroVol;
	}
	
	
	public String getTypeAvion() {
		return typeAvion;
	}
	
	
	public void setTypeAvion(String typeAvion) {
		this.typeAvion = typeAvion;
	}
	
	
	public String getTerminal() {
		return terminal;
	}
	
	
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	
	public String getStatut() {
		return statut;
	}
	
	
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
	public String getdateChoisie() {
		return dateChoisie;
	}
	
	
	public void setdateChoisie(String dateChoisie) {
		this.dateChoisie = dateChoisie;
	}
	
	
	public String getheureChoisie() {
		return heureChoisie;
	}
	
	
	public void setheureChoisie(String heureChoisie) {
		this.heureChoisie = heureChoisie;
	}
	
	
	public String getDateChoisie() {
		return dateChoisie;
	}
	
	
	public void setDateChoisie(String dateChoisie) {
		this.dateChoisie = dateChoisie;
	}
	
	
	public String getHeureChoisie() {
		return heureChoisie;
	}
	
	
	public void setHeureChoisie(String heureChoisie) {
		this.heureChoisie = heureChoisie;
	}
	
	
	public String getMomentVol() {
		return momentVol;
	}
	
	
	public void setMomentVol(String momentVol) {
		this.momentVol = momentVol;
	}
	
	
	public String getMessageSucces() {
		return messageSucces;
	}
	
	
	public void setMessageSucces(String messageSucces) {
		this.messageSucces = messageSucces;
	}
	
	
	public String geterreurLecture() {
		return erreurLecture;
	}
	
	
	public void seterreurLecture(String erreurLecture) {
		this.erreurLecture = erreurLecture;
	}
	
	
	public String getRegexDate() {
		return regexDate;
	}
	
	
	public String getRegexHeure() {
		return regexHeure;
	}
	
	
	public String getmessageErreur() {
		return messageErreur;
	}
	
	
	public void setmessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}
	
	
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	
	public HttpSession getSession() {
		
		FacesContext contexteJSF = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) contexteJSF.getExternalContext().getSession(true);
		return session;
	}
	
	
	public Vol getVol() {
		return vol;
	}
	
	
	public void setVol(Vol vol) {
		this.vol = vol;
	}
	
	
	public String getErreurLecture() {
		return erreurLecture;
	}
	
	
	public void setErreurLecture(String erreurLecture) {
		this.erreurLecture = erreurLecture;
	}
	
	
	public String getMessageErreur() {
		return messageErreur;
	}
	
	
	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}
	//=========================================================================//
}
