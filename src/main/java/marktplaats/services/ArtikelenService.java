package marktplaats.services;

import marktplaats.dao.CategorieDao;
import marktplaats.dao.GebruikerDao;
import marktplaats.dao.ArtikelDao;
import marktplaats.dao.ZoekDao;
import marktplaats.domain.*;
import marktplaats.dto.ArtikelDto;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Dependent
public class ArtikelenService {

    @Inject
    private ZoekDao zoekDao;

    @Inject
    private CategorieDao categorieDao;

    @Inject
    private GebruikerDao gebruikerDao;

    @Inject
    private ArtikelDao artikelDao;

    public List<Product> getProducten(long id) {
        return zoekDao.zoekProductOp(id);
    }

    public List<Categorie> getCategorieen() {
        return categorieDao.getAlleDistinctCategorieen();
    }

    public Product mapProductDtoNaarProduct(ArtikelDto artikelDto) {
        Product nieuwProduct = new Product();

        nieuwProduct.setArtikelNaam(artikelDto.getArtikelNaam());
        nieuwProduct.setCategorie(artikelDto.getCategorie());
        nieuwProduct.setOmschrijving(artikelDto.getOmschrijving());
        nieuwProduct.setPrijs(artikelDto.getPrijs());

        nieuwProduct.setBezorgwijzen(Arrays.asList(Bezorgwijze.Versturen));

//        nieuwProduct.setBijlagen(artikelDto.getBijlagen());
//        nieuwProduct.setBezorgwijzen(artikelDto.getBezorgwijzen());

        return nieuwProduct;
    }

    public void verkoopProduct(Product teVerkopenProduct) {

//        String wachtwoord = "mijnWachtwoord";
        Gebruiker gebruiker = gebruikerDao.getGebruikerMetEmail("simon@marktplaats.nl");

        gebruiker.voegArtikelToeAanLijstVanTeVerkopenArtikelen(teVerkopenProduct);

        gebruikerDao.updateGebruiker(gebruiker);
    }
}
