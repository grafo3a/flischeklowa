package net.apasajb.flischeklowa.operateurs;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;
import net.apasajb.flischeklowa.outils.SensVol;


/**
 * <br/>Fournit des methodes permettant de:<br/>
 * - lire le paquet de ressources "modelesVol";<br/>
 * - lister les modeles;<br/>
 * - ajouter un jour de vols et<br/>
 * - afficher un resultat via une page JSF.
 * @author ApasaJB
 */

@Named
@RequestScoped
public class JSFOperationsSurModelesVols implements Serializable {
	
	private static final long serialVersionUID = 8360627173725582497L;
	
	String codeCompagnie;
	String codePays;
	String compagnie;
	LocalDateTime dateHeure;
	SensVol sens;
	String ville;
	String typeAvion;
	String terminal;
	LocalDate nouvelleDate;
	String listeModelesVol = "";
	private String messageErreur = "";
	private String messageSucces = "";
	private String[] tabMessageSucces;
	private String[] tabMessageErreur;
	private String[] tableauModelesVol;
	private OperationsORM operationsORM = new OperationsORM();
	ArrayList<String[]> tabDynamique = new ArrayList<String[]>();
	
	//--- Les heures d'ouverture de l'aeroport de 09h a 23h
	final String[] horaire = {
			"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23"};
	
	//--- On cree un paquet de ressources (un objet de type ResourceBundle)
	final String nomBase = "net.apasajb.flischeklowa.ressourcesG11n.modelesVol";
	ResourceBundle paquet = ResourceBundle.getBundle(nomBase);
	
	
	public void afficherListeModelesVol() {
		this.utiliserModelesVol(ModeAcces.LIRE);
	}
	
	
	public void ajouterJourVols() {
		this.utiliserModelesVol(ModeAcces.ECRIRE);
	}
	
	
	public void lireModeleVol(String cleVol) {
		
		String valeurVol = paquet.getString(cleVol);
		String[] infosVol = valeurVol.split("---");
		
		ville = infosVol[0];
		codePays = infosVol[1];
		compagnie = infosVol[2];
		codeCompagnie = infosVol[3];
		typeAvion = infosVol[4];
		terminal = infosVol[5];
		
		listeModelesVol = listeModelesVol + cleVol + "---" + sens + "---" + valeurVol + "\n";
	}
	
	
	public void ajouterVol(String cleVol, LocalDate nouvelleDate) {
		
		EntityManager em = EcouteurAppli.getEM();
		
		String dateVol = nouvelleDate.toString();
		String heureVol = cleVol.replace("H", ":");
		String chaineDateHeureVol = dateVol + "T" + heureVol;
		dateHeure = LocalDateTime.parse(chaineDateHeureVol);
		
		try {		//--- Ici les variables suivantes ont deja leurs valeurs: sens, ville, codePays, compagnie, codeCompagnie.
			
			operationsORM.persisterVol(sens, dateHeure, ville, codePays, compagnie, codeCompagnie, typeAvion, terminal, em);
			messageSucces = messageSucces + "--- Flight added successfully: " +
											dateVol + " " + heureVol + " " + sens + " " + ville + "\n";
			
		} catch (Exception ex) {
			
			messageErreur = messageErreur + "--- Loading the flight failed for: " + cleVol +
					" (Message: " + operationsORM.getErreurAjoutVol() + ")\n";
		}
	}
	
	
	public String utiliserModelesVol(ModeAcces mode) {
		
		LocalDate nouvelleDate = this.getNouvelleDate(); //--- Calcul d'une nouvelle date
		EntityManager em = EcouteurAppli.getEM();
		
		//--- Chargement des vols DEPART
		sens = SensVol.Departure;
		
		for (String heureDepart : horaire) {
			
			String cleDepart = heureDepart + "H00";
			this.lireModeleVol(cleDepart);
			
			if (mode == ModeAcces.ECRIRE) {
				
				if (em != null) {
					
					this.ajouterVol(cleDepart, nouvelleDate);
					listeModelesVol = listeModelesVol + "\n";
					
				} else {
					
					messageErreur = messageErreur +
							"\n--- Error: No EntityManager found! flight not loaded for: " + cleDepart;
				}
			}
		}
		
		listeModelesVol = listeModelesVol + "\n";
		
		//--- Chargement des vols ARRIVEE
		sens = SensVol.Arrival;
		
		for (String heureArrivee : horaire) {
			
			String cleArrivee = heureArrivee + "H30";
			this.lireModeleVol(cleArrivee);
			
			if (mode == ModeAcces.ECRIRE) {
				
				if (em != null) {
					
					this.ajouterVol(cleArrivee, nouvelleDate);
					listeModelesVol = listeModelesVol + "\n";
					
				} else {
					
					messageErreur = messageErreur +
								"\n--- Error: No EntityManager found! flight not loaded for: " + cleArrivee ;
				}
			}
		}
		
		try { em.close(); } catch (Exception ignore) {}
		
		tableauModelesVol = listeModelesVol.split("\n");
		
		//--- On construit une collection (tableau dynamique) a partir du tableau statique
		for (String ligneTab : tableauModelesVol) {
			
			String[] tabLigne = ligneTab.split("---");
			tabDynamique.add(tabLigne);
		}
		
		tabMessageSucces = messageSucces.split("\n");
		tabMessageErreur = messageErreur.split("\n");
		
		return "liste-modeles-vol.xhtml";
	}
	
	
	public LocalDate getNouvelleDate() {
		
		EntityManager em = null;
		nouvelleDate = null;
		
		try {
			
			em = EcouteurAppli.getEM();
			LocalDateTime dateHeureMax;
			LocalDate dateMax;
			
			String reqNombreMax = "SELECT COUNT(v.dateHeure) FROM Vol v";
			Query reqNombre = em.createQuery(reqNombreMax);
			Long nombreVols = (Long) reqNombre.getSingleResult();
			
			if (nombreVols > 0) {
				
				String reqDateMax = "SELECT MAX(v.dateHeure) FROM Vol v";
				Query req = em.createQuery(reqDateMax);
				dateHeureMax = (LocalDateTime) req.getSingleResult();
				dateMax = dateHeureMax.toLocalDate();
				
				if (dateMax.isBefore(LocalDate.now()) || dateMax == null) {
					nouvelleDate = LocalDate.now();
					
				} else {
					nouvelleDate = dateMax.plusDays(1);
				}
				
			} else {
				nouvelleDate = LocalDate.now();
			}
			
		} catch (Exception ignore) {}
		
		try { em.close(); } catch (Exception ignore) {}
		
		return nouvelleDate;
	}
	
	
	//====================================================== GETTERS/SETTERS //
	
	public void setNouvelleDate(LocalDate nouvelleDate) {
		this.nouvelleDate = nouvelleDate;
	}
	
	
	public String[] getTableauModelesVol() {
		return tableauModelesVol;
	}
	
	
	public void setTableauModelesVol(String[] tableauModelesVol) {
		this.tableauModelesVol = tableauModelesVol;
	}
	
	
	public ArrayList<String[]> getTabDynamique() {
		return tabDynamique;
	}
	
	
	public void setTabDynamique(ArrayList<String[]> tabDynamique) {
		this.tabDynamique = tabDynamique;
	}
	
	
	public String getMessageErreur() {
		return messageErreur;
	}
	
	
	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}
	
	
	public String getMessageSucces() {
		return messageSucces;
	}
	
	
	public void setMessageSucces(String messageSucces) {
		this.messageSucces = messageSucces;
	}
	
	
	public String getCodeCompagnie() {
		return codeCompagnie;
	}
	
	
	public void setCodeCompagnie(String codeCompagnie) {
		this.codeCompagnie = codeCompagnie;
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
	
	
	public String getListeModelesVol() {
		return listeModelesVol;
	}
	
	
	public void setListeModelesVol(String listeModelesVol) {
		this.listeModelesVol = listeModelesVol;
	}
	
	
	public String[] getTabMessageSucces() {
		return tabMessageSucces;
	}
	
	
	public void setTabMessageSucces(String[] tabMessageSucces) {
		this.tabMessageSucces = tabMessageSucces;
	}
	
	
	public String[] getTabMessageErreur() {
		return tabMessageErreur;
	}
	
	
	public void setTabMessageErreur(String[] tabMessageErreur) {
		this.tabMessageErreur = tabMessageErreur;
	}
	//=========================================================================//
}
