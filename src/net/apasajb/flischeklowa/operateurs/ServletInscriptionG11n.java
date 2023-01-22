package net.apasajb.flischeklowa.operateurs;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.tinylog.Logger;
import net.apasajb.flischeklowa.ecouteurs.EcouteurAppli;
import net.apasajb.flischeklowa.outils.BoiteLogin;
import net.apasajb.flischeklowa.outils.Validation;
import net.apasajb.flischeklowa.outils.ValidationImple;


/**
 * Servlet qui gere la creation d'un compte avec globalisation G11N/I18N.
 * @author ApasaJB
 */
@WebServlet("/inscription-g11n")
public class ServletInscriptionG11n extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
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
		String S06_ERREUR_MOT2P_X2 = paquetServlets.getString("S06_ERREUR_MOT2P_X2");
		
		String paramCourriel = null;
		String paramMot2p01 = null;
		String paramMot2p02 = null;
		String erreurCourriel = null;
		String erreurMot2p01 = null;
		String erreurMot2p02 = null;
		String erreurContrat = null;
		String erreurInscription = null;
		boolean isCourrielValid = false;
		boolean isMot2pValid01 = false;
		boolean isMot2pValid02 = false;
		boolean isContractAccepted = false;
		Validation validation = new ValidationImple();
		
		if (request.getParameter("courriel").isEmpty() == false) {
			// Si le parametre courriel present
			
			paramCourriel = request.getParameter("courriel");
			isCourrielValid = validation.isCourrielValid(paramCourriel);
			
			if ( isCourrielValid == false) {
				erreurCourriel = S02_ERREUR_COURRIEL;
			}
			
		} else {
			// Si le parametre courriel absent
			
			erreurCourriel = S01_ERREUR_COURRIEL;
			isCourrielValid = false;
		}
		
		if (request.getParameter("mot2p01").isEmpty() == false) {
			// Si mot de passe 01 present
			
			paramMot2p01 = request.getParameter("mot2p01");
			isMot2pValid01 = validation.isMot2passeValid(paramMot2p01);
			
			if (isMot2pValid01 == false) {
				
				erreurMot2p01 = S04_ERREUR_MOT2P;
				request.setAttribute("erreurMot2p01", erreurMot2p01);
			}
			
		} else {
			// Si mot de passe 01 absent
			
			isMot2pValid01 = false;
			erreurMot2p01 = S03_ERREUR_MOT2P;
			request.setAttribute("erreurMot2p01", erreurMot2p01);
		}
		
		if (request.getParameter("mot2p02").isEmpty() == false) {
			// Si mot de passe 02 present
			
			paramMot2p02 = request.getParameter("mot2p02");
			
			if (paramMot2p02.equals(paramMot2p01)) {
				isMot2pValid02 = true;
				
			} else {
				
				isMot2pValid02 = false;
				erreurMot2p02 = S06_ERREUR_MOT2P_X2;
				request.setAttribute("erreurMot2p02", erreurMot2p02);
			}
			
		} else {
			// Si mot de passe 02 absent
			
			isMot2pValid02 = false;
			erreurMot2p02 = S03_ERREUR_MOT2P;
			request.setAttribute("erreurMot2p02", erreurMot2p02);
		}
		
		if (request.getParameter("contrat") != null) {
			// Si la case contrat cochée, on la coche en retour
			
			isContractAccepted = true;
			request.setAttribute("checkboxContrat", "checked");
			
		} else {
			// Si contrat d'utilisation non accepté
			
			isContractAccepted = false;
			erreurContrat = S05_ERREUR_CONTRAT;
		}
		
		if (isCourrielValid && isMot2pValid01 && isMot2pValid02 && isContractAccepted) {
			// Si identifiants acceptés, on est ensuite inscrit & connecté
			
			// Inscription & persistance du nouveau compte
			boolean isInscriptionOK = false;
			EntityManager em = EcouteurAppli.getEM();		// safe, returns em or null
			
			if (em != null) {
				// Si em present
				
				try {
					OperationsORMComptes operationsORMComptes = new OperationsORMComptes();
					operationsORMComptes.persisterCompte(paramCourriel, paramMot2p02, em);		// La methode fermera em
					isInscriptionOK = true;
					Logger.info("New account persistence OK.");
					Logger.info("Account created successfully: " + paramCourriel);
					
				} catch (Exception ex) {
					
					erreurInscription = ex.getMessage();
					Logger.error(erreurInscription);
					request.setAttribute("erreurInscription", erreurInscription);
				}
				
			} else {
				// Si em absent
				
				erreurInscription = "EntityManager not found!";
				Logger.error(erreurInscription);
				request.setAttribute("erreurInscription", erreurInscription);
			}
			
			if (isInscriptionOK) {
				// Connexion du nouveau compte (creation d'un cookie)
				BoiteLogin.creerCookie(paramCourriel, response, session);
				
				// Reponse pour l'utilisateur
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion-g11n.jsp");
				rd.forward(request, response);
				
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription-g11n.jsp");
				rd.forward(request, response);
			}
			
		} else {
			// Si identifiants refusés
			
			request.setAttribute("erreurCourriel", erreurCourriel);
			request.setAttribute("erreurContrat", erreurContrat);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription-g11n.jsp");
			rd.forward(request, response);
		}
	}
}
