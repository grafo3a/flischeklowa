package net.apasajb.flischeklowa.filtres;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Filtre qui gere l'acces aux classes accessibles par *.jsf et controle-pool.
 * @author ApasaJB
 */

@WebFilter(
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR },
		urlPatterns = { "*.jsf", "/controle-emfactory" }
		)
public class FiltreConnexion implements Filter {
	
	
	public void destroy() {}
	
	
	public void doFilter(ServletRequest servletReq, ServletResponse servletRes, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletReq;
		HttpServletResponse response = (HttpServletResponse) servletRes;
		HttpSession session = request.getSession();
		
		//--- Si on est deconnectee
		if (session.getAttribute("courrielCookie") == null) {
			
			Cookie[] listeCookies = request.getCookies();
			
			if (listeCookies != null) {		//--- Si qlq cookies sont presents
				
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
		}
		
		//--- Si on est deconnectE, redirection vers la page de connexion
		if (session.getAttribute("courrielCookie") == null) {
			response.sendRedirect(request.getContextPath() + "/connexion-g11n");
			
		} else {		// Si on est connectE, on passe A la prochaine etape apres ce filtre (on sort de ce filtre)
			chain.doFilter(servletReq, servletRes);
		}
	}
	
	
	public void init(FilterConfig fConfig) throws ServletException {}
	
}
