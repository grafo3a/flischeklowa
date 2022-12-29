package net.apasajb.flischeklowa.operateurs;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.apasajb.flischeklowa.outils.Validation;
import net.apasajb.flischeklowa.outils.ValidationImple;


/**
 * Servlet qui gere la connexion au moyen d'une adresse courriel et d'un cookie avec globalisation G11N/I18N.
 * @author ApasaJB
 */

@WebServlet("/connexion-g11n")
public class ServletConnexionG11n extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static int dureeVieCookieSecondes = 20;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Cookie[] listeCookies = request.getCookies();
		
		if (listeCookies != null) {		// Si des cookies sont presents
			
			String nomCookie = null;
			String valeurCookie = null;
			
			for (Cookie cookie : listeCookies) {
				
				nomCookie = cookie.getName();
				valeurCookie = cookie.getValue();
				
				if (nomCookie == "courriel09") {
					session.setAttribute("courrielCookie", valeurCookie);
				}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion-g11n.jsp");
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// SI ON RESSOIT UN FORMULAIRE ON EST FORCEMENT DECONNECTE
		
		HttpSession session = request.getSession();
		
		// Preparation de G11N: on identifie la culture de l'utilisateur
		Locale locale = request.getLocale();
		
		// On cree un paquet de ressources (un objet ResourceBundle)
		String nomBase = "net.apasajb.flischeklowa.ressourcesG11n.paquetServlets";
		ResourceBundle paquetServlets = ResourceBundle.getBundle(nomBase, locale);
		
		// On extrait les messages du paquet de ressources
		String S01_ERREUR_COURRIEL = paquetServlets.getString("S01_ERREUR_COURRIEL");
		String S02_ERREUR_COURRIEL = paquetServlets.getString("S02_ERREUR_COURRIEL");
		String S03_ERREUR_MOT2P = paquetServlets.getString("S03_ERREUR_MOT2P");
		String S04_ERREUR_MOT2P = paquetServlets.getString("S04_ERREUR_MOT2P");
		String S05_ERREUR_CONTRAT = paquetServlets.getString("S05_ERREUR_CONTRAT");
		
		String paramCourriel = null;
		String paramMot2p = null;
		String erreurCourriel = null;
		String erreurMot2p = null;
		String erreurContrat = null;
		int longueurMot2p = 0;
		boolean courrielValide = false;
		boolean mot2pValide = false;
		boolean contratAcceptee = true;
		
		
		if (request.getParameter("courriel").isEmpty() == false) {		// Si le parametre courriel present
			
			paramCourriel = request.getParameter("courriel");
			
			Validation validation = new ValidationImple();
			courrielValide = validation.validerCourriel(paramCourriel);		// validation de l'adresse courriel
			
		} else {		// Si le parametre courriel absent
			
			erreurCourriel = S01_ERREUR_COURRIEL;
			courrielValide = false;
		}
		
		
		if ( courrielValide == false) {		// Si le courriel non valide
			erreurCourriel = S02_ERREUR_COURRIEL;
		}
		
		
		if (request.getParameter("mot2p").isEmpty() == false) {		// Si mot de passe present
			
			paramMot2p = request.getParameter("mot2p");
			longueurMot2p = paramMot2p.length();
			
		} else {		// Si Mot de passe absent
			
			mot2pValide = false;
			erreurMot2p = S03_ERREUR_MOT2P;
		}
		
		if (longueurMot2p >= 5) {
			mot2pValide = true;
			
		} else {
			
			erreurMot2p = S04_ERREUR_MOT2P;
			mot2pValide = false;
		}
		
		if (request.getParameter("contrat") == null) {		// Si contrat d'utilisation acceptE ou non
			
			contratAcceptee = false;
			erreurContrat = S05_ERREUR_CONTRAT;
			
		} else {
			
			// Si case contrat cochee, on la coche en retour
			session.setAttribute("checkboxContrat", "checked");
		}
		
		// Si connexion acceptee, on est connectee.
		if ((courrielValide == true) && (mot2pValide == true) && (contratAcceptee == true)) {
			
			Cookie cookie09 = new Cookie("courriel09", paramCourriel);		// On installe un cookie
			cookie09.setMaxAge(60 * dureeVieCookieSecondes);		// La duree de vie du cookie en secondes
			response.addCookie(cookie09);
			session.setAttribute("courrielCookie", paramCourriel);
			
		} else {		// Si connexion refusee
			
			session.setAttribute("erreurCourriel", erreurCourriel);
			session.setAttribute("erreurMot2p", erreurMot2p);
			session.setAttribute("erreurContrat", erreurContrat);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion-g11n.jsp");
		rd.forward(request, response);
	}
}
