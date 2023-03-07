package net.apasajb.flischeklowa.outils;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet permettant de controler manuellement le pool de connexions.
 * @author ApasaJB
 */

@WebServlet("/controle-pool")
public class ServletControlePoolConnexion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/controle-connexions.jsp");
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sletContext = request.getServletContext();
		String demande = null;
		String resultatPool = null;
		
		demande = request.getParameter("demande");
		
		if (demande.equals("demarrerPool")) {
			
			// On demarre le pool
			BoitePoolConnexion objetPool = (BoitePoolConnexion) new BoitePoolConnexionImple();
			objetPool.demarrerPool(sletContext);
			resultatPool = objetPool.getResultatPool();
			
		} else {
			// Si demande == "arreterPool"
			
			BoitePoolConnexion objetPool = (BoitePoolConnexion) new BoitePoolConnexionImple();
			objetPool.arreterPool(sletContext);
			resultatPool = objetPool.getResultatPool();
		}
		
		request.setAttribute("resultatPool", resultatPool);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/controle-connexions.jsp");
		rd.forward(request, response);
	}
}
