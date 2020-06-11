package marktplaats.services;

import marktplaats.dao.ArtikelDao;
import marktplaats.dao.CategorieDao;
import marktplaats.domain.Artikel;
import marktplaats.domain.Categorie;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VerkoopProductService {

    List<Categorie> categorieen = new ArrayList<>();

    @Inject
    ArtikelDao artikelDao;

    @Inject
    CategorieDao categorieDao;

    public List<Artikel> getArtikel(long id) {
        return artikelDao.zoekId(id);
    }

}
