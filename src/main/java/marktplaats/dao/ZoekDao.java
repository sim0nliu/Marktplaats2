package marktplaats.dao;

import marktplaats.domain.Artikel;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ZoekDao {

    @PersistenceContext
    EntityManager em;

    public List<Artikel> zoekArtikelenOp(long index) {
        TypedQuery<Artikel> query = em.createQuery("select a from Artikel a WHERE a.id = :id", Artikel.class);
        query.setParameter("id", index);
        return query.getResultList();
    }

    public List<Gebruiker> zoekGebruikerOp(long index) {
        TypedQuery<Gebruiker> query = em.createQuery("select g from Gebruiker g WHERE g.id = :id", Gebruiker.class);
        query.setParameter("id", index);
        return query.getResultList();
    }

    public List<Product> zoekProductOp(long index) {
        TypedQuery<Product> query = em.createQuery("SELECT p from Product p WHERE p.id = :id", Product.class);
        query.setParameter("id", index);
        return query.getResultList();
    }
}
