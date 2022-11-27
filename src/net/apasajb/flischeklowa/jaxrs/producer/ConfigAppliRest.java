package net.apasajb.flischeklowa.jaxrs.producer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
* Active la prise en charge de toutes les requetes "/vols-rest" par Jersey
* @author ApasaJB
*/

@ApplicationPath("/vols-rest")		// C'est l'equivalent de webapi ailleurs
public class ConfigAppliRest extends Application {
	// On ne fait rien ici
}
