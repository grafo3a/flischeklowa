package net.apasajb.flischeklowa.passagers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Affichage de la page JSP d'infos techniques du projet.
 * @author ApasaJB
 */

@WebServlet("/projet")
public class ServletProjet extends HttpServlet {
	
	private static final long serialVersionUID = 7382714379266092422L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/projet.jsp");
		rd.forward(request, response);
	}
}
