package net.apasajb.flischeklowa.outils;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Permet de tester si la base de donnees est accessible.
 * @author ApasaJB
 */
@WebServlet("/test-jdbc")
public class ServletTestJDBC extends HttpServlet {
	
	private static final long serialVersionUID = -9067325248534096457L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String messageEtatBDD = BoiteConnexionJDBC.testerConnexionJDBC();
		request.setAttribute("messageEtatBDD", messageEtatBDD);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/controle-connexions.jsp");
		rd.forward(request, response);
	}
}
