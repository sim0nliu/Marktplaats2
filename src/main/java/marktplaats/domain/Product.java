package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Product extends Artikel {

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "bezorgwijzeproduct")
    @Enumerated(EnumType.STRING)
    private List<Bezorgwijze> bezorgwijzen;

    //BLOB
    private byte[] bijlagen;

    public Product() {
    }

    public Product(Set<Categorie> categorie, String artikelNaam, String omschrijving, BigDecimal prijs, List<Bezorgwijze> bezorgwijzen) {
        setCategorie(categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
        setBezorgwijzen(bezorgwijzen);
    }

//    public void setBezorgwijzen(List<Bezorgwijze> bezorgwijzen) {
//        this.bezorgwijzen = bezorgwijzen;
//    }
}
