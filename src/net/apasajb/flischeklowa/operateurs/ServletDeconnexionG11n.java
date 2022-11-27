package net.apasajb.flischeklowa.operateurs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Gere une deconnexion en mode globalisee G11N/i18n.
 * @author ApasaJB
 */

@WebServlet("/deconnexion-g11n")
public class ServletDeconnexionG11n extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// On obtient la session pour l'annuler.
		HttpSession session = request.getSession();
		
		// Le cookie associE A la session actuelle
		String courrielCookie = session.getAttribute("courrielCookie").toString();
		
		// On detruit le cookie associE A la session actuelle:
		// On installe un cookie avec 0 secondes de vie (technique pour detruire un cookie)
		Cookie cookieObsolete = new Cookie("courriel09", courrielCookie);
		cookieObsolete.setMaxAge(0);
		response.addCookie(cookieObsolete);
		
		// On se deconnecte de la session
		session.setAttribute("courrielCookie", null);
		session.setAttribute("erreurCourriel", null);
		session.setAttribute("erreurMot2p", null);
		session.setAttribute("erreurContrat", null);
		
		// On redirige le resultat A la servlet de connexion
		String contexte = request.getContextPath();
		response.sendRedirect(contexte + "/connexion-g11n");
	}
}
