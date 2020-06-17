package marktplaats.dao;

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

    public List<Product> zoekProductOp(long index) {
        TypedQuery<Product> query = em.createQuery("SELECT p from Product p WHERE p.id = :id", Product.class);
        query.setParameter("id", index);
        return query.getResultList();
    }

    public List<Product> alleProducten() {
        TypedQuery<Product> query = em.createQuery("SELECT p from Product p", Product.class);
        return query.getResultList();
    }
}
