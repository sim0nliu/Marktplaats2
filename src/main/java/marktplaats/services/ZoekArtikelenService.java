package marktplaats.services;

import marktplaats.dao.ZoekDao;
import marktplaats.domain.Artikel;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.Product;

import javax.inject.Inject;
import java.util.List;

public class ZoekArtikelenService {

    @Inject
    ZoekDao zoekDao;

    public List<Artikel> getArtikelen(long id) {
        return zoekDao.zoekArtikelenOp(id);
    }

    public List<Product> getProducten(long id) { return zoekDao.zoekProductOp(id); }

    public List<Gebruiker> getGeberuikers(long id) { return zoekDao.zoekGebruikerOp(id); }

}
