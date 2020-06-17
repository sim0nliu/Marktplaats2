package marktplaats.services;

import marktplaats.dao.ZoekDao;
import marktplaats.domain.Product;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class ZoekArtikelenService {

    @Inject
    private ZoekDao zoekDao;

    public List<Product> getProduct(long id) {
        return zoekDao.zoekProductOp(id);
    }

    public List<Product> getAlleProducten() {
        return zoekDao.alleProducten();
    }

}
