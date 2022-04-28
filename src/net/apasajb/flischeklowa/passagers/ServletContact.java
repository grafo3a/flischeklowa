package net.apasajb.flischeklowa.passagers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Affichage la page JSP de contact.
 * @author ApasaJB
 */

@WebServlet("/contact")
public class ServletContact extends HttpServlet {
	
	private static final long serialVersionUID = -2779003699212982163L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/contact.jsp");
		rd.forward(request, response);
	}
}
