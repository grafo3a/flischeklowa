package net.apasajb.flischeklowa.passagers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Prend en charge les requetes Ajax de la page horaire-vols.jsp.<br/>
 * @author ApasaJB
 */

@WebServlet("/horaire-vols-ajax")
public class ServletHoraireVolsAjax extends HttpServlet {
	
	private static final long serialVersionUID = -8756471561888409670L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AffichageHoraireVols traitements = new AffichageHoraireVols();
		request = traitements.traiterRequeteUtilisateur(request);
		String tableHoraireVols = (String) request.getAttribute("tableHoraireVols");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(tableHoraireVols);
	}
}
