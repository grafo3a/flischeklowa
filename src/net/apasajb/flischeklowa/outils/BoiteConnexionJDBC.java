package net.apasajb.flischeklowa.outils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.tinylog.Logger;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;


/**
 * Offre une methode qui fournit une connexion JDBC, par defaut via un Pool de connexions,<br/>
 * si non en cas d'echec, via DriverManager.
 * @author ApasaJB
 */
public class BoiteConnexionJDBC {
	
	// On cree un paquet de ressources et on en extrait les infos pour la connexion
	final static String nomBase = "net.apasajb.flischeklowa.ressourcesG11n.paquetBDD";
	final static ResourceBundle paquetIdentifiants = ResourceBundle.getBundle(nomBase);
	final static String URL = paquetIdentifiants.getString("url");
	final static String USER = paquetIdentifiants.getString("user");
	final static String PASSWORD = paquetIdentifiants.getString("password");
	final static String DRIVER_CLASS = paquetIdentifiants.getString("driver_class");
	
	
	public static Connection getConnection() {
		
		Connection connexion = null;
		
		// Obtention d'un pool de connexions et d'une connexion
		try {
			ServletContext contexte = EcouteurAppli.getServletContext();
			DataSource poolConnexion = (DataSource)  contexte.getAttribute("poolConnex");
			Logger.info("--- Connection pool found. Hcode: " + poolConnexion.hashCode());
			connexion = poolConnexion.getConnection();
			Logger.info("--- Connection found via the connection pool. Hcode: " + connexion.hashCode());
			
		} catch (Exception ex) {
			
			Logger.info("--- Connection not found via the connection pool. Trying with DriverManager ...");
			
			try {
				// Chargement du pilote & obtention d'une connexion via DriverManager
				Class.forName(DRIVER_CLASS);
				connexion = DriverManager.getConnection(URL, USER, PASSWORD);
				Logger.info("--- Connection found via DriverManager. Hcode: " + connexion.hashCode());
				
			} catch (Exception exce) {
				Logger.info("--- ERROR: Connection not found. Message: " + exce.getMessage());
			}
		}
		
		return connexion;
	}
	
	
	public static String testerConnexionJDBC() {
		
		String messageEtatBDD;
		Connection connexion = BoiteConnexionJDBC.getConnection();		// Si vide on ressoit null sans erreur
		
		if (connexion != null) {
			
			try {
				DatabaseMetaData metadata = connexion.getMetaData();
				messageEtatBDD = "<font color=\"green\">The database is responding. DB details: "
						+ metadata.getDatabaseProductName() + " "
						+ metadata.getDatabaseProductVersion() + "</font>";
			} catch (Exception ex) {
				
				messageEtatBDD = "<font color=\"red\">--- ERROR: " + ex.getMessage() + "</font>";
				Logger.info("--- ERROR: " + ex.getMessage());
			
			} finally {
				
				try { connexion.close(); } catch (Exception ignore) {}
			}
			
		} else {
			messageEtatBDD = "<font color=\"red\">The database is not accessible</font>";
		}
		
		return messageEtatBDD;
	}
}
