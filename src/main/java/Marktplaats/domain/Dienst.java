package Marktplaats.domain;

import java.math.BigDecimal;
import java.util.List;

public class Dienst extends Artikel {
    public Dienst() {

    }

    public Dienst(List<Categorie> categorie, String artikelNaam, String omschrijving, BigDecimal prijs) {
        setCategorie(categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
    }
}
