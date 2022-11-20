package net.apasajb.flischeklowa.passagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import javax.servlet.http.HttpServletRequest;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;
import net.apasajb.flischeklowa.outils.BoiteConnexionJDBC;


/**
 * Offre des methodes qui traitent une requete ressue<br/>
 * et qui generent la liste de vols de la date actuelle, sous forme d'un tableau HTML, dans une longue chaine de caracteres.
 * @author ApasaJB
 */
public class AffichageHoraireVols {
	
	public HttpServletRequest traiterRequeteUtilisateur(HttpServletRequest request) {
		
		ResultSet resultSet = null;
		String paramPays = null;
		String paramSens = null;
		String tableHoraireVols = "";
		String messageErreur = "";
		String remarqueSQL = "";
		
		FuseauxHoraires.actualiserAffichageDate(request);
		
		String fuseauHoraire = EcouteurAppli.getFuseauhoraire();
		ZoneId zoneKlow = ZoneId.of(fuseauHoraire);
		OffsetDateTime dateHeureKlow = OffsetDateTime.now(zoneKlow);
		LocalDate dateAujourdhui = dateHeureKlow.toLocalDate();
		
		String critereDate = dateAujourdhui.toString();
		Connection connexion = BoiteConnexionJDBC.getConnection();
		
		if (connexion != null) {
			
			try {
				String requeteSQL;
				int casRequete = 0;
				
				// Ici tabSens est un tableau (Array), il ne sera jamais vide.
				String[] tabSens = request.getParameterValues("sens");
				paramSens = tabSens[0];
				
				if (paramSens.equals("All") && request.getParameter("pays").isEmpty()){
					
					requeteSQL = "select * FROM vols55 WHERE TO_CHAR(date_heure, 'YYYY-MM-DD') =? ORDER BY date_heure";
					remarqueSQL = "Info: No criteria chosen! You can filter by direction or country.";
					casRequete = 1;
					
				} else if (paramSens.equals("All") && request.getParameter("pays").isEmpty() == false) {
					
					requeteSQL = "select * FROM vols55 WHERE TO_CHAR(date_heure, 'YYYY-MM-DD') =? AND code_pays =? ORDER BY date_heure";
					paramPays = request.getParameter("pays").toUpperCase();
					casRequete = 2;
					
				} else if (!paramSens.equals("All") && request.getParameter("pays").isEmpty()) {
					
					requeteSQL = "select * FROM vols55 WHERE TO_CHAR(date_heure, 'YYYY-MM-DD') =? AND sens =? ORDER BY date_heure";
					casRequete = 3;
					
				} else {
					
					paramPays = request.getParameter("pays").toUpperCase();
					requeteSQL = "SELECT * FROM vols55 WHERE TO_CHAR(date_heure, 'YYYY-MM-DD') =? AND sens =? AND code_pays =? ORDER BY date_heure";
					casRequete = 4;
				}
				
				// Preparation de la requete preparee
				PreparedStatement requetePreparee = connexion.prepareStatement(requeteSQL);
				requetePreparee.setString(1, critereDate);
				
				if (casRequete == 1) {
					// On ne fait rien ici: la requete garde la forme par defaut
					
				} else if (casRequete == 2) {
					requetePreparee.setString(2, paramPays);
					
				} else if (casRequete == 3) {
					requetePreparee.setString(2, paramSens);
					
				} else {
					
					requetePreparee.setString(2, paramSens);
					requetePreparee.setString(3, paramPays);
				}
				
				resultSet = requetePreparee.executeQuery();
				
				String styleOK = "style=\"background-color: #d9d9d9\"";
				String tdDefault = "<td>";
				String tdCompleted = "<td " + styleOK + ">";		//--- Fond gris faible pour l'heure deja passee
				
				// Construction d'une table pour faciliter son exploitation par Ajax
				tableHoraireVols =
						"<table class=\"table table-sm border\">"
						+ "<thead class=\"text-secondary\">"
						+ "<tr>"
						+ "<th scope=\"col\">Time</th>"
						+ "<th scope=\"col\">Direction</th>"
						+ "<th scope=\"col\">City</th>"
						+ "<th scope=\"col\">Country</th>"
						+ "<th scope=\"col\">Company</th>"
						+ "<th scope=\"col\">Gate</th>"
						+ "<th scope=\"col\">Status</th>"
						+ "</tr>"
						+ "</thead>"
						+ "<tbody>";
				
				int numeroLigne = 0;
				OffsetTime heureKlow = OffsetTime.now(zoneKlow);
				
				String dateHeureVol = null;
				LocalTime heureVol = null;
				String sensVol = null;
				String nomCompagnie = null;
				String nomVille = null;
				String codePays = null;
				//String numVol = null;
				//String typeAvion = null;
				String numTerminal = null;
				String statutVol = null;
				boolean isStatutCalculable = false;
				
				// Lecture du resultSet
				while (resultSet.next()) {
					
					String tdTag = tdDefault;
					dateHeureVol = resultSet.getString(4);
					dateHeureVol = dateHeureVol.replace(" ", "T");
					LocalDateTime dateHeure = LocalDateTime.parse(dateHeureVol);
					
					heureVol = dateHeure.toLocalTime();
					sensVol = resultSet.getString(6);
					nomVille = resultSet.getString(10);
					codePays = resultSet.getString(2);
					nomCompagnie = resultSet.getString(3);
					//numVol = resultSet.getString(5);
					//typeAvion = resultSet.getString(9);
					numTerminal = resultSet.getString(8);
					statutVol = resultSet.getString(7);
					isStatutCalculable = false;		//--- on reinitialise la valeur
					
					Duration duree = Duration.between(heureVol, heureKlow);
					int dureeMinutes = (int) duree.toMinutes();
					//--- NB: duree negative = avant le vol (temps restant),
					//--- NB: duree positive = apres le vol (temps ecoulE).
					
					if(sensVol.equals("Departure") && statutVol.equals("Confirmed")) {
						isStatutCalculable = true;
					}
					
					//--- On colore le statut selon le nombre de minutes restantes ou ecoulees
					if (isStatutCalculable) {
						
						if (dureeMinutes >= -120 && dureeMinutes < -40) {
							statutVol = "<font color=\"blue\">Gate open</font>";
							
						} else if (dureeMinutes >= -40 && dureeMinutes < -20) {
							statutVol = "<font color=\"blue\">Boarding</font>";
							
						} else if (dureeMinutes >= -20  && dureeMinutes < 1) {
							statutVol = "<font color=\"red\">Gate closed</font>";
							
						} else if (dureeMinutes >= 1  && dureeMinutes < 10) {
							statutVol = "<font color=\"green\">Departing</font>";
							
						} else if (dureeMinutes >= 10 ) {
							statutVol = "Departed";
						}
					}
					
					if (sensVol.equals("Arrival") && dureeMinutes >= 1) {
						statutVol = "Arrived";
					}
					
					if (statutVol.equals("Departed") || statutVol.equals("Arrived")) {
						tdTag = tdCompleted;		//--- On colore le fond en gris
					}
					
					numeroLigne = resultSet.getRow();
					
					String ligneTableHoraire = "<tr>"
							+ tdTag + heureVol + "</td>"
							+ tdTag + sensVol + "</td>"
							+ tdTag + nomVille + "</td>"
							+ tdTag + codePays + "</td>"
							+ tdTag + nomCompagnie + "</td>"
							//+ tdTag + numVol + "</td>"
							//+ tdTag + typeAvion + "</td>"
							+ tdTag + numTerminal + "</td>"
							+ tdTag + statutVol + "</td>"
							+ "</tr>\n";
					
					tableHoraireVols = tableHoraireVols + ligneTableHoraire;
				}
				
				if (numeroLigne == 0) {		//--- Si numeroLigne tjrs 0 donc resultSet est vide.
					tableHoraireVols = "<font color=\"red\">[Info] Ohhh, no flight found!</font>";
				}
				
				tableHoraireVols = tableHoraireVols + "</tbody></table>";
				
			} catch (SQLException sqlex) {
				messageErreur = "ERROR. Type: SQL. Message: " + sqlex.getMessage();
				
			} catch (Exception ex) {
				messageErreur = "ERROR. Type: Any. Message: " + ex.getMessage();
				
			} finally {
				
				try {
					connexion.close();
					resultSet.close();
					
				} catch (Exception ignore) {}
			}
			
		} else {
			messageErreur = "--- ERROR: The connection failed. Check manually if the database is accessible.";
		}
		
		remarqueSQL = "<font color='blue'><i>" + remarqueSQL + "</i></font>";
		messageErreur = "<font color='red'><i>" + messageErreur + "</i></font>";
		tableHoraireVols = tableHoraireVols + "<br/>" + remarqueSQL + messageErreur;
		
		request.setAttribute("messageErreur", messageErreur);
		request.setAttribute("tableHoraireVols", tableHoraireVols);
		
		return request;
	}
}
