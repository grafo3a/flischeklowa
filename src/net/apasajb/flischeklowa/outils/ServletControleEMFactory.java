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
 * Servlet permettant de controler manuellement une fabrique JPA (EMFactory)
 * @author ApasaJB
 */

@WebServlet("/controle-emfactory")
public class ServletControleEMFactory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/controle-connexions.jsp");
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sletContext = request.getServletContext();
		String demande = null;
		String resultatEmfactory = null;
		BoiteEMFactoryImple objetFabrique = new BoiteEMFactoryImple();
		
		demande = request.getParameter("demande");
		
		if (demande.equals("demarrerEmfactory")) {
			
			objetFabrique.demarrerEMFactory(sletContext);		// On demarre emFactory
			resultatEmfactory = objetFabrique.getResultatEMFactory();
			
		} else {		// Si demande == "arreterFabrique"
			
			objetFabrique.arreterEMFactory(sletContext);	
			resultatEmfactory = objetFabrique.getResultatEMFactory();
		}
		
		request.setAttribute("resultatEmfactory", resultatEmfactory);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/controle-connexions.jsp");
		rd.forward(request, response);
	}
}
