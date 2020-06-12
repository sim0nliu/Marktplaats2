package marktplaats.dao;

import marktplaats.domain.Artikel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class ArtikelDao {

    @PersistenceContext
    EntityManager em;

    public void artikelToevoegen(Artikel artikel) {
        em.persist(artikel);
    }

    public Artikel updateArtikel(Artikel artikel) {
        Artikel merged = em.merge(artikel);
        em.getTransaction().commit();
        return merged;
    }


    public List<Artikel> zoekId(long index) {
        TypedQuery<Artikel> query = em.createQuery("select a from Artikel a WHERE a.id = :id", Artikel.class);
        query.setParameter("id", index);
        return query.getResultList();
    }

    public List<Artikel> zoekOpNaam(String zoekterm) {
        zoekterm = "%" + zoekterm + "%";
        TypedQuery<Artikel> query = em.createQuery("SELECT a FROM Artikel a WHERE  a.artikelNaam LIKE :naam", Artikel.class);
        query.setParameter("naam", zoekterm);
        return query.getResultList();
    }

    public List<Artikel> zoekUitgebreid(String categorie, String naam, BigDecimal prijsMiniaal, BigDecimal prijsMaximaal) {
        naam = "%" + naam + "%";

        TypedQuery<Artikel> query = em.createQuery("SELECT a " +
                "FROM Artikel a " +
                "JOIN a.categorie cat " +
                "WHERE cat.categorieNaam = :categorie " +
                "AND a.artikelNaam LIKE :naam " +
                "AND a.prijs >= :prijsMinimaal " +
                "AND a.prijs <= :prijsMaximaal", Artikel.class);

        query.setParameter("categorie", categorie);
        query.setParameter("naam", naam);
        query.setParameter("prijsMinimaal", prijsMiniaal);
        query.setParameter("prijsMaximaal", prijsMaximaal);

        return query.getResultList();
    }
}
