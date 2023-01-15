package net.apasajb.flischeklowa.operateurs;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import net.apasajb.flischeklowa.dao.Compte;


/**
 * <br/>Fournit des methodes permettant de:<br/>
 * - Trouver un compte sur base d'une adresse courriel;<br/>
 * - Persister un compte en BDD.<br/>
 * @author ApasaJB
 */

public class OperationsORMComptes {
	
	/**
	 * Recherche un compte sur base d'une adresse courriel.
	 * @return Compte
	 */
	public Compte trouverCompteViaCourriel (String adresseCourriel, EntityManager em) {
		
		Compte compte = null;
		
		try {
			String chaineReq = "SELECT c FROM Compte c WHERE c.courrielCompte = :email";		// JPQL
			TypedQuery<Compte> requete = em.createQuery(chaineReq, Compte.class);
			requete.setParameter("email", adresseCourriel);
			compte = (Compte)requete.getSingleResult();
			
		} catch (Exception ignore) {}
		
		return compte;
	}
	
	
	public void persisterCompte(String adresseCourriel, String mot2passe, EntityManager em) {
		
		Compte compte = new Compte();
		compte.setCourrielCompte(adresseCourriel);
		compte.setMot2Passe(mot2passe);
		
		try {
			em.getTransaction().begin();
			em.persist(compte);
			em.getTransaction().commit();
		
		} catch (Exception ex) {
			em.getTransaction().rollback();
		
		} finally {
			try { em.close(); } catch (Exception ignore) {}
		}
	}
}
