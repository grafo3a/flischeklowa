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
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import net.apasajb.flischeklowa.passagers.FuseauxHoraires;


/**
 * Actualise la valeur du decalage horaire (entre le client et le serveur) pour l'affichage de la date.
 * @author ApasaJB
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST },
		urlPatterns = { "/*" }
		)
public class FiltreFuseauxHoraires extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = -6012032100373872183L;
	
	public void destroy() {}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest requete = (HttpServletRequest)request;
		FuseauxHoraires.actualiserAffichageDate(requete);
		chain.doFilter(request, response);
	}
	
	
	public void init(FilterConfig fConfig) throws ServletException {}
}
