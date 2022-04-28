package net.apasajb.flischeklowa.config;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;


/**
 * Active l'injection des dependances geree par Weld
 * @author ApasaJB
 */

@ApplicationScoped
@FacesConfig( version = FacesConfig.Version.JSF_2_3 )		//--- Activation de CDI
public class ConfigCdiJsf {
	
	//--- On ne fait rien ici.
}
