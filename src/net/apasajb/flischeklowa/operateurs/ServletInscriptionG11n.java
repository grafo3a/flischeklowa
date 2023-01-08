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
 * Servlet qui gere la creation d'un compte avec globalisation G11N/I18N.
 * @author ApasaJB
 */

@WebServlet("/inscription-g11n")
public class ServletInscriptionG11n extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static int dureeVieCookieSecondes = 30;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription-g11n.jsp");
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		String paramMot2p01 = null;
		String paramMot2p02 = null;
		String erreurCourriel = null;
		String erreurMot2p01 = null;
		String erreurMot2p02 = null;
		String erreurContrat = null;
		int longueurMot2p01 = 0;
		int longueurMot2p02 = 0;
		int nombreMinimumCaracteres = 5;
		boolean isValidCourriel = false;
		boolean isValidmot2p01 = false;
		boolean isValidmot2p02 = false;
		boolean isContractAccepted = true;
		
		
		if (request.getParameter("courriel").isEmpty() == false) {		// Si le parametre courriel present
			
			paramCourriel = request.getParameter("courriel");
			
			Validation validation = new ValidationImple();
			isValidCourriel = validation.validerCourriel(paramCourriel);		// validation de l'adresse courriel
			
		} else {		// Si le parametre courriel absent
			
			erreurCourriel = S01_ERREUR_COURRIEL;
			isValidCourriel = false;
		}
		
		
		if ( isValidCourriel == false) {		// Si courriel non valide
			erreurCourriel = S02_ERREUR_COURRIEL;
		}
		
		
		// Si mot de passe 01 present
		if (request.getParameter("mot2p01").isEmpty() == false) {
			
			paramMot2p01 = request.getParameter("mot2p01");
			longueurMot2p01 = paramMot2p01.length();
			
			
			if (longueurMot2p01 >= nombreMinimumCaracteres) {		// une verif basique complexifiable
				isValidmot2p01 = true;
				
			} else {
				
				isValidmot2p01 = false;
				erreurMot2p01 = S04_ERREUR_MOT2P;
				request.setAttribute("erreurMot2p01", erreurMot2p01);
			}
			
		} else {
			
			isValidmot2p01 = false;
			erreurMot2p01 = S03_ERREUR_MOT2P;
			request.setAttribute("erreurMot2p01", erreurMot2p01);
		}
		
		
		// Si mot de passe 02 present
		if (request.getParameter("mot2p02").isEmpty() == false) {
			
			paramMot2p02 = request.getParameter("mot2p02");
			longueurMot2p02 = paramMot2p02.length();
			
			if (longueurMot2p02 >= nombreMinimumCaracteres) {		// une verif basique complexifiable
				isValidmot2p02 = true;
				
			} else {
				
				isValidmot2p02 = false;
				erreurMot2p02 = S04_ERREUR_MOT2P;
				request.setAttribute("erreurMot2p02", erreurMot2p02);
			}
			
		} else {
			
			isValidmot2p02 = false;
			erreurMot2p02 = S03_ERREUR_MOT2P;
			request.setAttribute("erreurMot2p02", erreurMot2p02);
		}
		
		
		if (request.getParameter("contrat") == null) {		// Si contrat d'utilisation acceptE ou non
			
			isContractAccepted = false;
			erreurContrat = S05_ERREUR_CONTRAT;
			
		} else {		// Si case contrat cochee, on la coche en retour
			request.setAttribute("checkboxContrat", "checked");
		}
		
		// Si identifiants acceptEs, on est inscrit & connectE
		if (isValidCourriel && isValidmot2p01 && isValidmot2p02 && isContractAccepted) {
			
			// Inscription: persistance du nouveau compte [...]
			
			Cookie cookie09 = new Cookie("courriel09", paramCourriel);		// On installe un cookie
			cookie09.setMaxAge(60 * dureeVieCookieSecondes);		// La duree de vie du cookie en secondes
			response.addCookie(cookie09);
			session.setAttribute("courrielCookie", paramCourriel);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion-g11n.jsp");
			rd.forward(request, response);
			
		} else {
			// Si identifiants refusEs
			
			request.setAttribute("erreurCourriel", erreurCourriel);
			request.setAttribute("erreurContrat", erreurContrat);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription-g11n.jsp");
			rd.forward(request, response);
		}
	}
}
