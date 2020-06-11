package marktplaats.dao;

import marktplaats.domain.Categorie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class CategorieDao {
    @PersistenceContext
    EntityManager em;

    public void categorieToevoegen(Categorie categorie) {
        em.persist(categorie);
    }

    public void categorieVerwijderenMetNaam(String naamCategorie) {
        List<Categorie> select = categorieVindenMetNaam(naamCategorie);
        if (select.get(0).getCategorieNaam().equals(naamCategorie)) {
            em.remove(select.get(0));
        }
    }

//    public List<String> vindAlleDistinctCategorieen() {
//        Query query = em.createNativeQuery("SELECT DISTINCT categorieNaam FROM Categorie");
//        return query.getResultList();
//    }

    private List<Categorie> categorieVindenMetNaam(String naamCategorie) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Categorie> query = cb.createQuery(Categorie.class);

        Root<Categorie> categorie = query.from(Categorie.class);
        query.select(categorie).distinct(true).where(cb.equal(categorie.get("categorieNaam"), naamCategorie));

        return em.createQuery(query).getResultList();
    }

    public List<Categorie> getAlleCategorieen() {
        Query query = em.createNativeQuery("SELECT categorieNaam FROM Categorie");
        return query.getResultList();
    }
}
