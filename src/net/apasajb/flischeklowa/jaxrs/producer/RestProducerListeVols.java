package net.apasajb.flischeklowa.jaxrs.producer;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;
import net.apasajb.flischeklowa.jaxrs.beans.CollectionVolsRest;
import net.apasajb.flischeklowa.jaxrs.beans.VolRest;


/**
 * Classe ressource Jersey (Jax-RS), prend en charge les requetes REST.<br/>
 * @author ApasaJB
 */

@Path("/")
public class RestProducerListeVols {
	
	CollectionVolsRest collectionVolsRest = EcouteurAppli.getCollectionVolsRest();
	
	
	// Prise en charge des requetes par defaut "/"
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getVols() {
		
		return "Welcome to FlischeKlowa web services.\n"
				+ "[Info] To see information, add \"/xml\" or \"/json\" on your URL.";
	}
	
	
	//======================================================= PRODUCTION XML //
	
	@GET
	@Path("/xml")
	@Produces(MediaType.TEXT_PLAIN)
	public String getVolsXml() {
		
		return "[Info] XML format chosen.\n"
				+ "[a] To get a list of flights for a date, add a date on your URL (e.g. /vols-rest/xml/2022-01-01)\n"
				+ "[b] To get a list of flights for a date and a country, use the country code (e.g. /vols-rest/xml/2022-01-01/PL)\n"
				+ "[c] To get a flight for a date and a flight number, use the number (e.g. /vols-rest/xml/2022-01-01/JAV01)";
	}
	
	
	// Prise en charge des requetes Get XML pour une date
	@GET
	@Path("/xml/{dateRest: (2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])}")
	@Produces(MediaType.APPLICATION_XML)
		public ArrayList<VolRest> getVolsXmlDate(@PathParam("dateRest") String dateRest) {
		
		ArrayList<VolRest> listeVolsRest = collectionVolsRest.compilerCollectionVols(LocalDate.parse(dateRest), "%", "%", "%");
		
		return listeVolsRest;
	}
	
	
	// Prise en charge des requetes Get XML pour une Date et un Pays
	@GET
	@Path("/xml/{dateRest: (2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])}/{codePaysRest: [a-zA-Z]{2}}")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<VolRest> getVolsXMLDateCodePays(
			@PathParam("dateRest") String dateRest,
			@PathParam("codePaysRest") String codePaysRest) {
		
		codePaysRest = codePaysRest.toUpperCase();
		ArrayList<VolRest> listeVolsRest = collectionVolsRest.compilerCollectionVols(LocalDate.parse(dateRest), "%", codePaysRest, "%");
		
		return listeVolsRest;
	}
	
	
	// Prise en charge des requetes Get XML pour une Date et un numero de vol
	@GET
	@Path("/xml/{dateRest: (2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])}/{numeroVolRest: [a-zA-Z]{3}[0-9]{2}}")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<VolRest> getVolsXMLDateNumeroVol(
			@PathParam("dateRest") String dateRest,
			@PathParam("numeroVolRest") String numeroVolRest) {
		
		numeroVolRest = numeroVolRest.toUpperCase();
		ArrayList<VolRest> listeVolsRest = collectionVolsRest.compilerCollectionVols(LocalDate.parse(dateRest), "%", "%", numeroVolRest);
		
		return listeVolsRest;
	}
	
	
	// Prise en charge des requetes Get XML pour une Date et un sens/une direction
	@GET
	@Path("/xml/{dateRest: (2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])}/{sensVolRest: (departure)|(arrival)}")
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<VolRest> getVolsXMLDateSens(
			@PathParam("dateRest") String dateRest,
			@PathParam("sensVolRest") String sensVolRest) {
		
		ArrayList<VolRest> listeVolsRest = collectionVolsRest.compilerCollectionVols(LocalDate.parse(dateRest), sensVolRest, "%", "%");
		
		return listeVolsRest;
	}
	
	
	//====================================================== PRODUCTION JSON //
	
	@GET
	@Path("/json")
	@Produces(MediaType.TEXT_PLAIN)
	public String getVolsJson() {
		
		return "[Info] JSON format chosen.\n"
				+ "[a] To get a list of flights for a date, add a date on your URL (e.g. /vols-rest/json/2022-01-01)\n"
				+ "[b] To get a list of flights for a date and a country, use the country code (e.g. /vols-rest/json/2022-01-01/PL)\n"
				+ "[c] To get a flight for a date and a flight number, use the number (e.g. /vols-rest/json/2022-01-01/JAV01)";
	}
	
	
	// Prise en charge des requetes Get JSON pour une date
	@GET
	@Path("/json/{dateRest: (2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])}")
	@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<VolRest> getVolsJsonDate(@PathParam("dateRest") String dateRest) {
		
		ArrayList<VolRest> listeVolsRest = collectionVolsRest.compilerCollectionVols(LocalDate.parse(dateRest), "%", "%", "%");
		
		return listeVolsRest;
	}
	
	
	// Prise en charge des requetes Get JSON pour une Date et un Pays
	@GET
	@Path("/json/{dateRest: (2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])}/{codePaysRest: [a-zA-Z]{2}}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<VolRest> getVolsJsonDateCodePays(
			@PathParam("dateRest") String dateRest,
			@PathParam("codePaysRest") String codePaysRest) {
		
		codePaysRest = codePaysRest.toUpperCase();
		ArrayList<VolRest> listeVolsRest = collectionVolsRest.compilerCollectionVols(LocalDate.parse(dateRest), "%", codePaysRest, "%");
		
		return listeVolsRest;
	}
	
	
	// Prise en charge des requetes Get JSON pour une Date et un numero de vol
	@GET
	@Path("/json/{dateRest: (2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])}/{numeroVolRest: [a-zA-Z]{3}[0-9]{2}}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<VolRest> getVolsJsonDateNumeroVol(
			@PathParam("dateRest") String dateRest,
			@PathParam("numeroVolRest") String numeroVolRest) {
		
		numeroVolRest = numeroVolRest.toUpperCase();
		ArrayList<VolRest> listeVolsRest = collectionVolsRest.compilerCollectionVols(LocalDate.parse(dateRest), "%", "%", numeroVolRest);
		
		return listeVolsRest;
	}
	
	
	// Prise en charge des requetes Get JSON pour une Date et un sens/une direction
	@GET
	@Path("/json/{dateRest: (2[0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])}/{sensVolRest: (departure)|(arrival)}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<VolRest> getVolsJsonDateSens(
			@PathParam("dateRest") String dateRest,
			@PathParam("sensVolRest") String sensVolRest) {
		
		ArrayList<VolRest> listeVolsRest = collectionVolsRest.compilerCollectionVols(LocalDate.parse(dateRest), sensVolRest, "%", "%");
		
		return listeVolsRest;
	}
}
