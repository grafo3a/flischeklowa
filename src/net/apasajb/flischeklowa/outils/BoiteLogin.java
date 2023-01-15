package net.apasajb.flischeklowa.outils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Offre des methodes relatives au processus de login.
 * @author ApasaJB
 */
public class BoiteLogin {
	
	final static int dureeVieCookieMinutes = 60;
	
	
	public static void creerCookie(String paramCourriel, HttpServletResponse response, HttpSession session) {
		
		Cookie cookie09 = new Cookie("courriel09", paramCourriel);		// On installe un cookie
		cookie09.setMaxAge(60 * dureeVieCookieMinutes);					// La duree de vie du cookie en secondes
		response.addCookie(cookie09);
		session.setAttribute("courrielCookie", paramCourriel);
	}
}
