package net.apasajb.flischeklowa.passagers;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.servlet.http.HttpServletRequest;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;


/**
 * Se charge d'actualiser le nombre d'heures de decalage du fuseau horaire de Klow (UTC+02:00 ou autre).<br/>
 * Grace A cette classe, l'application s'adapte correctement aux changements de fuseau horaire.
 * @author ApasaJB
 */
public class FuseauxHoraires {
	
	public static void actualiserAffichageDate(HttpServletRequest requete) {
		
		String fuseauHoraire = EcouteurAppli.getFuseauhoraire();
		ZoneId idZoneKlow = ZoneId.of(fuseauHoraire);
		ZonedDateTime dateHeureKlow = ZonedDateTime.now(idZoneKlow);
		ZoneOffset decalageKlow = dateHeureKlow.getOffset();
		String texteDecalageKlow = "UTC" + decalageKlow.getId();		// ex: "UTC+02:00"
		int heuresDecalageKlow = decalageKlow.getTotalSeconds()/3600;		// ex: 2
		
		requete.setAttribute("texteDecalageKlow", texteDecalageKlow);
		requete.setAttribute("heuresDecalageKlow", heuresDecalageKlow);
	}
}
