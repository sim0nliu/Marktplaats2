package marktplaats.services;

import marktplaats.dao.ArtikelDao;
import marktplaats.dao.CategorieDao;
import marktplaats.dao.GebruikerDao;
import marktplaats.domain.Artikel;
import marktplaats.domain.Categorie;
import marktplaats.domain.Gebruiker;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class VerkoopProductService {

    @Inject
    ArtikelDao artikelDao;

    @Inject
    CategorieDao categorieDao;

    @Inject
    GebruikerDao gebruikerDao;

    public List<Artikel> getArtikel(long id) {
        return artikelDao.zoekId(id);
    }

    public List<Categorie> getCategorieen() {
        return categorieDao.getAlleDistinctCategorieen();
    }


    public Artikel verkoopArtikel(Artikel artikel) {

        String wachtwoord = "haarWachtwoord";
        Gebruiker gebruiker = gebruikerDao.getGebruikerMetEmail("chantal@marktplaats.nl");

        gebruiker.voegArtikelToeAanLijstVanTeVerkopenArtikelen(artikel);
        return artikel;
    }
}
