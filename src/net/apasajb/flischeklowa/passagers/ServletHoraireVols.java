package net.apasajb.flischeklowa.passagers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Affiche un horaire de vols sur base des criteres sens et codePays choisis
 * pour la date d'aujourdhui.<br/>
 * Technologies utilisees: Servlet, JSP, DataSource, PreparedStatement.
 * @author ApasaJB
 */

@WebServlet (value={"", "/horaire-vols"})		//--- La valeur vide prend en charge correctement l'URL "/"
public class ServletHoraireVols extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FuseauxHoraires.actualiserAffichageDate(request);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/horaire-vols.jsp");
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AffichageHoraireVols traitements = new AffichageHoraireVols();
		request = traitements.traiterRequeteUtilisateur(request);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/horaire-vols.jsp");
		rd.forward(request, response);
	}
}
