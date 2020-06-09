package marktplaats.dao;

import marktplaats.domain.Gebruiker;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class GebruikerDao {
    @PersistenceContext
    EntityManager em;

    public void gebruikerToevoegen(Gebruiker gebruiker) {
        em.persist(gebruiker);
    }

    public Gebruiker updateGebruiker(Gebruiker gebruiker) {
        Gebruiker merged = em.merge(gebruiker);
        em.getTransaction().commit();
        return merged;
    }

    public Gebruiker getGebruikerEmailEnWachtwoord(String email, String wachtwoord) {
        TypedQuery<Gebruiker> query = em.createQuery("SELECT g FROM Gebruiker g WHERE  g.email = :email AND g.wachtwoord = :wachtwoord", Gebruiker.class);
        query.setParameter("email", email);
        query.setParameter("wachtwoord", wachtwoord);
        return query.getSingleResult();
    }
}
