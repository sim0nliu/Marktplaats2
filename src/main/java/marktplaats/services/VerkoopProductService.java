package marktplaats.services;

import marktplaats.dao.CategorieDao;
import marktplaats.dao.GebruikerDao;
import marktplaats.dao.ArtikelDao;
import marktplaats.domain.Artikel;
import marktplaats.domain.Categorie;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.Product;
import marktplaats.dto.ArtikelDto;

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


    public Product mapProductDtoNaarProduct(ArtikelDto artikelDto) {
        Product nieuwProduct = new Product();

        nieuwProduct.setArtikelNaam(artikelDto.getArtikelNaam());
        // nieuwProduct.setCategorie(artikelDto.getCategorie());
        nieuwProduct.setOmschrijving(artikelDto.getOmschrijving());
        nieuwProduct.setPrijs(artikelDto.getPrijs());
        return nieuwProduct;
    }
}
