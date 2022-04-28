package net.apasajb.flischeklowa.passagers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Affichage la page JSP d'infos A-propos de FlischeKlowa.
 * @author ApasaJB
 */

@WebServlet("/a-propos")
public class ServletFlischeKlowa extends HttpServlet {
	
	private static final long serialVersionUID = 3197721014116843028L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/a-propos.jsp");
		rd.forward(request, response);
	}
}
