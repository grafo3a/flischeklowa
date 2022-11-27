package net.apasajb.flischeklowa.outils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


/**
 * Implementation de BoitePoolConnexion
 * @author ApasaJB
 */
public class BoitePoolConnexionImple implements BoitePoolConnexion {
	
	private DataSource poolConnex = new DataSource();
	private String resultatPool = null;
	
	// On cree un paquet de ressources et on en extrait les infos pour la connexion
	final String nomBase = "net.apasajb.flischeklowa.ressourcesG11n.paquetBDD";
	final ResourceBundle paquetIdentifiants = ResourceBundle.getBundle(nomBase);
	final String URL = paquetIdentifiants.getString("url");
	final String USER = paquetIdentifiants.getString("user");
	final String PASSWORD = paquetIdentifiants.getString("password");
	final String DRIVER_CLASS = paquetIdentifiants.getString("driver_class");
	
	
	public void demarrerPool(ServletContext servletContext) {
		
		PoolProperties pp = new PoolProperties();
		Connection connexion = null;
		
		try {		// Configuration du pool
			pp.setUrl(URL);
			pp.setDriverClassName(DRIVER_CLASS);
			pp.setUsername(USER);
			pp.setPassword(PASSWORD);
			pp.setJmxEnabled(true);
			pp.setTestWhileIdle(false);
			pp.setTestOnBorrow(true);
			pp.setValidationQuery("SELECT 1");
			pp.setTestOnReturn(false);
			pp.setValidationInterval(30000);
			pp.setTimeBetweenEvictionRunsMillis(30000);
			pp.setMaxActive(100);
			pp.setInitialSize(10);
			pp.setMaxWait(10000);
			pp.setRemoveAbandonedTimeout(60);
			pp.setMinEvictableIdleTimeMillis(30000);
			pp.setMinIdle(10);
			pp.setLogAbandoned(true);
			pp.setRemoveAbandoned(true);
			//pp.setJdbcInterceptors(
			//"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
			//"org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
			pp.setJdbcInterceptors("ConnectionState;StatementFinalizer");
			
			// On demarre le pool
			poolConnex.setPoolProperties(pp);
			
			// On recolte qlqs infos sur la BDD
			connexion = poolConnex.getConnection();
			DatabaseMetaData mdata = connexion.getMetaData();
			String infosBDD = "DB Type: " + mdata.getDatabaseProductName() + " ; Version: " + mdata.getDatabaseProductVersion();
			resultatPool = "--- The connection pool has started successfully. " + infosBDD;
			
		} catch (Exception ex) {
			
			resultatPool = "--- ERROR: The connection pool failed to start! Message: " + ex.getMessage();
			poolConnex = null;
			
		} finally {
			
			if (connexion != null) try { connexion.close(); } catch (Exception ignore) {}
		}
		
		this.setResultatPool(resultatPool);		// On enregistre le resultat de l'operation pour affichage dans l'IHM
		servletContext.setAttribute("poolConnex", poolConnex);
	}
	
	
	public void arreterPool(ServletContext servletContext) {
		
		try {
			
			DataSource poolConnex = (DataSource) servletContext.getAttribute("poolConnex");
			poolConnex.close();
			servletContext.setAttribute("poolConnex", null);
			resultatPool = "--- The connection pool has been stopped.";
			this.setResultatPool(resultatPool);		// On enregistre le resultat de l'operation
			
		} catch (Exception ignore) {}
	}
	
	
	//====================================================== GETTERS/SETTERS //
	
	public DataSource getPoolConnex(ServletContext servletContext) {
		
		poolConnex = (DataSource) servletContext.getAttribute("poolConnex");
		return poolConnex;
	}
	
	
	public void setPoolConnex(DataSource poolConnex) {
		this.poolConnex = poolConnex;
	}
	
	
	public String getResultatPool() {
		return resultatPool;
	}
	
	
	public void setResultatPool(String resultatPool) {
		this.resultatPool = resultatPool;
	}
	//==========================================================//
}
