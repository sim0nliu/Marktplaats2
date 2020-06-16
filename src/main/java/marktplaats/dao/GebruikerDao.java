package marktplaats.dao;

import marktplaats.dao.exceptions.GebruikerNotFoundException;
import marktplaats.domain.AbstracteEntiteit;
import marktplaats.domain.Gebruiker;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class GebruikerDao {

    @PersistenceContext
    EntityManager em;

    public void gebruikerToevoegen(Gebruiker gebruiker) {
        em.persist(gebruiker);
    }

    //TODO: Batch updates
    public void gebruikersToevoegen(Gebruiker... x)  {
        for (Gebruiker p: x) {
            gebruikerToevoegen(p);
        }
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

    public Gebruiker selecteerOpEmail(String email) throws GebruikerNotFoundException {
        TypedQuery<Gebruiker> selecteerOpEmail = em.createQuery("select p from Gebruiker p where p.email = :firstarg", Gebruiker.class);
        selecteerOpEmail.setParameter("firstarg", email);
        List<Gebruiker> resultList = selecteerOpEmail.getResultList();
        if(resultList.size() == 1){
            return resultList.get(0);
        }else {
            throw new GebruikerNotFoundException();
        }
    }

    public boolean bestaatGebruiker(String email){
        TypedQuery<Gebruiker> selecteerOpEmail = selecteerOpEmail = em.createQuery("select p from Gebruiker p where p.email = :firstarg", Gebruiker.class);
        selecteerOpEmail.setParameter("firstarg", email);
        List<Gebruiker> resultList = selecteerOpEmail.getResultList();
        if(resultList.size() == 1){
            return true;
        }else{
            //Aanname hier is dat email @Column(unique = true)
            return false;
        }
    }

    public Gebruiker getGebruikerMetEmail(String email) {
        TypedQuery<Gebruiker> query = em.createQuery("SELECT g FROM Gebruiker g WHERE  g.email = :email", Gebruiker.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
