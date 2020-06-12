package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends Artikel {

    private boolean afhalenThuis;
    private boolean afhalenMagazijn;
    private boolean versturen;
    private boolean versturenOnderRembours;

    //BLOB
    private byte[] bijlagen;

    public Product() {
    }

    public Product(List<Categorie> categorie, String artikelNaam, String omschrijving, BigDecimal prijs, boolean afhalenThuis, boolean afhalenMagazijn, boolean versturen, boolean versturenOnderRembours) {
        setCategorie(categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
        setAfhalenThuis(afhalenThuis);
        setAfhalenMagazijn(afhalenMagazijn);
        setVersturen(versturen);
        setVersturenOnderRembours(versturenOnderRembours);
    }
}
