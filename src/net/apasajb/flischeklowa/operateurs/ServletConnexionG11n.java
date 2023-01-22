package net.apasajb.flischeklowa.operateurs;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;
import net.apasajb.flischeklowa.outils.BoiteLogin;
import net.apasajb.flischeklowa.outils.Validation;
import net.apasajb.flischeklowa.outils.ValidationImple;


/**
 * Servlet qui gere la connexion au moyen d'une adresse courriel et d'un cookie avec globalisation G11N/I18N.
 * @author ApasaJB
 */
@WebServlet("/connexion-g11n")
public class ServletConnexionG11n extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Cookie[] listeCookies = request.getCookies();
		
		if (listeCookies != null) {
			// Si des cookies sont presents
			
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
		String S05_ERREUR_CONTRAT = paquetServlets.getString("S05_ERREUR_CONTRAT");
		
		String paramCourriel = null;
		String paramMot2p = null;
		String erreurCourriel = null;
		String erreurMot2p = null;
		String erreurContrat = null;
		boolean isCourrielValid = false;
		boolean isMot2pCorrect = false;
		boolean isContractAccepted = false;
		Validation validation = new ValidationImple();
		
		if (request.getParameter("courriel").isEmpty() == false) {
			// Si le parametre courriel present
			
			paramCourriel = request.getParameter("courriel");
			isCourrielValid = validation.isCourrielValid(paramCourriel);
			
			if ( isCourrielValid == false) {
				// Si courriel non valide
				erreurCourriel = S02_ERREUR_COURRIEL;
			}
			
		} else {
			// Si le parametre courriel absent
			
			erreurCourriel = S01_ERREUR_COURRIEL;
			isCourrielValid = false;
		}
		
		if (request.getParameter("mot2p").isEmpty() == false) {
			// Si mot de passe present
			
			EntityManager em = EcouteurAppli.getEM();
			paramMot2p = request.getParameter("mot2p");
			boolean isPasswordCorrectInDB = false;
			
			try {
				isPasswordCorrectInDB = validation.isPasswordCorrectInDB(paramCourriel, paramMot2p, em);
				
			} catch (Exception ignore) {}
			
			if (isPasswordCorrectInDB) {
				isMot2pCorrect = true;
			
			} else {
				erreurMot2p = validation.getErreurLogin();
			}
			
		} else {
			// Si Mot de passe absent
			erreurMot2p = S03_ERREUR_MOT2P;
		}
		
		
		if (request.getParameter("contrat") != null) {
			// Si case contrat cochée, on la coche en retour
			
			isContractAccepted = true;
			session.setAttribute("checkboxContrat", "checked");
			
		} else {
			// si contrat non accepté
			erreurContrat = S05_ERREUR_CONTRAT;
		}
		
		if (isCourrielValid && isMot2pCorrect && isContractAccepted) {
			// Si connexion acceptée, on est ensuite connecté.
			BoiteLogin.creerCookie(paramCourriel, response, session);
			
		} else {
			// Si connexion refusée
			
			request.setAttribute("erreurCourriel", erreurCourriel);
			request.setAttribute("erreurMot2p", erreurMot2p);
			request.setAttribute("erreurContrat", erreurContrat);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion-g11n.jsp");
		rd.forward(request, response);
	}
}
