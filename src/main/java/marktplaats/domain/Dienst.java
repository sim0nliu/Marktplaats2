package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Dienst extends Artikel {
    public Dienst() {

    }

    public Dienst(List<Categorie> categorie, String artikelNaam, String omschrijving, BigDecimal prijs) {
        setCategorie((Set<Categorie>) categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
    }
}
